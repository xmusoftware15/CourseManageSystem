package xmu.crms.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.crms.entity.Event;
import xmu.crms.mapper.TimerMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author YEE
 */
@Repository
public class TimerDAO {
    @Autowired
    private TimerMapper timerMapper;

    /**
     * 插入一个定时事件
     * @param time
     * @param beanName
     * @param methodName
     * @param paramMap
     */
    public void insertEvent(Date time, String beanName, String methodName, HashMap<BigInteger, String> paramMap) {}

    /**
     * 插入一个待执行事件
     * @param time
     * @param beanName
     * @param methodName
     * @param paramList
     */
    public void insertEvent(Date time, String beanName, String methodName, List<Object> paramList) {
        ObjectMapper m = new ObjectMapper();
        m.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, "type");

        try {
            String param = m.writeValueAsString(paramList);
            Event event = new Event();
            event.setTime(time);
            event.setBeanName(beanName);
            event.setMethodName(methodName);
            event.setParameter(param);
            timerMapper.insertEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有可执行事件
     * @return
     */
    public List<Event> getExecutableEvents() {
        List<Event> event = new ArrayList<>(16);
        try {
            event = timerMapper.getExecutableEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    /**
     * 根据事件的id获取事件
     * @param id
     * @return
     */
    public Event getEventById(BigInteger id) {
        Event event = null;
        try {
            event = timerMapper.getEventById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    /**
     * 根据eventId查找并更新事件的执行时间
     * @param eventId
     * @param time
     * @return Boolean
     */
    public Boolean updateEvent(BigInteger eventId, Date time) {
        try {
            Event event = timerMapper.getEventById(eventId);
            if (event == null) {
                return false;
            } else {
                timerMapper.updateEvent(eventId, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除事件
     * @param event
     */
    public void deleteEvent(Event event) {
        try {
            timerMapper.deleteEvent(event.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
