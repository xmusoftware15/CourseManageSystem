package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import xmu.crms.entity.Event;

/**
 * @author YEE
 */
@Component
@Mapper
public interface TimerMapper {

    /**
     * 插入一个定时事件
     * @param time
     * @param beanName
     * @param methodName
     * @param parameter
     */
    void insertEvent(@Param("time") Date time, @Param("bean_name") String beanName,
                     @Param("method_name") String methodName, @Param("parameter") String parameter);

    /**
     * 获取所有的可执行事件
     * @return
     */
    List<Event> getExecutableEvents();

    /**
     * 根据事件的id获取事件
     * @param id
     * @return
     */
    Event getEventById(BigInteger id);

    /**
     * 更新事件
     * @param id
     * @param time
     */
    void updateEvent(@Param("id") BigInteger id, @Param("time") Date time);

    /**
     * 删除事件
     * @param id
     */
    void deleteEvent(BigInteger id);
}
