package xmu.crms.service.impl;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xmu.crms.dao.TimerDAO;
import xmu.crms.service.TimerService;
import xmu.crms.entity.Event;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.*;
import java.lang.reflect.Constructor;

/**
 * 实现定时器任务
 * @author YEE
 */
@Service
public class TimerServiceImpl implements TimerService {

    @Autowired
    private TimerDAO timerDAO;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 插入一个定时事件
     * @param time 事件的时间
     * @param beanName 对象名
     * @param methodName 方法名
     * @param paramMap 方法参数
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void insertEvent(Date time, String beanName, String methodName, HashMap<BigInteger, String> paramMap) {
        try {
            timerDAO.insertEvent(time, beanName, methodName, paramMap);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据eventId查找并更新事件的执行时间
     * @param eventId
     * @param time
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void updateEvent(BigInteger eventId, Date time) {
        try {
            Date cur = new Date();
            // 10分钟前的时间
            Date tenBeforeCur = new Date(cur.getTime() - 1000 * 10 * 60);
            // 待执行时间在 -10min 之前的情况
            if(time.getTime() < tenBeforeCur.getTime()) {
                // TODO: Date不合法时的处理
            }
            // 待执行时间在 -10min ~ 当前时间 的情况
            if(time.getTime() > tenBeforeCur.getTime() && time.getTime() < cur.getTime()) {
                // TODO: 待执行时间在 -10min ~ 当前时间 时，立即执行？
            }
            timerDAO.updateEvent(eventId, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 每10min查找一次event表，处理
     * 注：查找当前时间到之前10min内的待执行事务
     */
    @Override
    @Scheduled(fixedRate = 1000 * 60 * 10)
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void scheduled() {
        List<Event> events = timerDAO.getExecutableEvents();
        if(events == null) {
            System.out.println("no event to do!");
            return;
        }
        for (Event event : events) {
            StringBuffer sb = new StringBuffer();
            try {
                Object bean = applicationContext.getBean(event.getBeanName());
                Method callback = BeanUtils.findDeclaredMethodWithMinimalParameters(bean.getClass(), event.getMethodName());

                JSONObject hostObject = new JSONObject(event.getParameter());
                Iterator<String> iterator = hostObject.keys();
                while(iterator.hasNext()){
                    // 获得key
                    String key = iterator.next();
                    // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
                    String value = hostObject.getString(key);
                    sb.append(value);
                    sb.append(",");
                }
                String target = sb.toString().substring(0, sb.toString().length() - 1);
                Object[] tempParams = target.split(",");

                // 参数类型数组
                Class[] paramTypes = callback.getParameterTypes();
                // 目标参数数组
                Object[] obj = new Object[paramTypes.length];

                for (int i = 0; i < paramTypes.length; i++) {
                    Class aimClass = Class.forName(paramTypes[i].getName().toString());
                    Class[] originalTypes = {String.class};
                    // 根据参数类型获取相应的构造函数
                    Constructor constructor = aimClass.getConstructor(originalTypes);
                    // 根据获取的构造函数和参数，创建实例
                    obj[i] = constructor.newInstance(tempParams[i]);
                }
                callback.invoke(bean, obj);
                //TODO: 异常处理

                //删除已执行的记录
                timerDAO.deleteEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
