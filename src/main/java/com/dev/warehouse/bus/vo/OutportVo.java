package com.dev.warehouse.bus.vo;


import com.dev.warehouse.bus.entity.Outport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class OutportVo extends Outport {

    private Integer page = 1;

    private Integer limit = 10;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getLimit()
    {
        return limit;
    }

    public void setLimit(Integer limit)
    {
        this.limit = limit;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
}
