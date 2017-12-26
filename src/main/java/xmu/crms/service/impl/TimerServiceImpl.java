package xmu.crms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xmu.crms.dao.TimerDAO;
import xmu.crms.entity.Event;
import xmu.crms.service.TimerService;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
     * 插入待执行的事务
     * @param time
     * @param beanName
     * @param methodName
     * @param paramList
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void insertEvent(Date time, String beanName, String methodName, List<Object> paramList) {
        try {
            timerDAO.insertEvent(time, beanName, methodName, paramList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据eventId查找并更新事件的执行时间
     * @param eventId
     * @param newTime
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void updateEvent(BigInteger eventId, Date newTime) {
        try {
            timerDAO.updateEvent(eventId, newTime);
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
            return;
        }

        try {
            for (Event event : events) {
                ObjectMapper om = new ObjectMapper();
                om.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, "type");

                Object bean = applicationContext.getBean(event.getBeanName());
                Method callback = BeanUtils.findDeclaredMethodWithMinimalParameters(bean.getClass(), event.getMethodName());
                Object[] args = om.readValue(event.getParameter(), Object[].class);
                callback.invoke(bean, args);
                timerDAO.deleteEvent(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
