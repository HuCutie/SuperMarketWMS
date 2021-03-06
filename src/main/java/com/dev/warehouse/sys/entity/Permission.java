package com.dev.warehouse.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pid;

    private String type;

    private String title;

    /**
     * 权限编码[只有type=permission才有 user:view]
     */
    private String percode;

    private String icon;

    private String href;

    private String target;

    private Integer open;

    private Integer ordernum;

    /**
     * 是否可用[0不可用，1可用]
     */
    private Integer available;

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getPid()
    {
        return pid;
    }

    public void setPid(Integer pid)
    {
        this.pid = pid;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPercode()
    {
        return percode;
    }

    public void setPercode(String percode)
    {
        this.percode = percode;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public Integer getOpen()
    {
        return open;
    }

    public void setOpen(Integer open)
    {
        this.open = open;
    }

    public Integer getOrdernum()
    {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum)
    {
        this.ordernum = ordernum;
    }

    public Integer getAvailable()
    {
        return available;
    }

    public void setAvailable(Integer available)
    {
        this.available = available;
    }
}
