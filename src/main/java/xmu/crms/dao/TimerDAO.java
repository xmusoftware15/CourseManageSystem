package xmu.crms.dao;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.crms.mapper.TimerMapper;
import xmu.crms.entity.Event;

import java.math.BigInteger;
import java.util.*;

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
    public void insertEvent(Date time, String beanName, String methodName, HashMap<BigInteger, String> paramMap){
        Map.Entry entry;
        Map<String, String> tempMap = new HashMap(16);
        for(Iterator iterator = paramMap.entrySet().iterator(); iterator.hasNext();) {
            entry = (Map.Entry)iterator.next();
            tempMap.put(entry.getKey().toString(), entry.getValue().toString());
        }

        JSONObject paramJSON = new JSONObject(tempMap);

        try {
            timerMapper.insertEvent(time, beanName, methodName, paramJSON.toString());
        } catch(Exception e) {
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
