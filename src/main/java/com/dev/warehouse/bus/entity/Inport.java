package com.dev.warehouse.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_inport")
public class Inport implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String paytype;

    private Date inporttime;

    private String operateperson;

    private Integer number;

    private String remark;

    private Double inportprice;

    private Integer providerid;

    private Integer goodsid;

    /**
     * 供应商姓名
     */
    @TableField(exist = false)
    private String providername;

    /**
     * 商品名称
     */
    @TableField(exist = false)
    private String goodsname;

    /**
     * 商品规格
     */
    @TableField(exist = false)
    private String size;

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

    public String getPaytype()
    {
        return paytype;
    }

    public void setPaytype(String paytype)
    {
        this.paytype = paytype;
    }

    public Date getInporttime()
    {
        return inporttime;
    }

    public void setInporttime(Date inporttime)
    {
        this.inporttime = inporttime;
    }

    public String getOperateperson()
    {
        return operateperson;
    }

    public void setOperateperson(String operateperson)
    {
        this.operateperson = operateperson;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number = number;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Double getInportprice()
    {
        return inportprice;
    }

    public void setInportprice(Double inportprice)
    {
        this.inportprice = inportprice;
    }

    public Integer getProviderid()
    {
        return providerid;
    }

    public void setProviderid(Integer providerid)
    {
        this.providerid = providerid;
    }

    public Integer getGoodsid()
    {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid)
    {
        this.goodsid = goodsid;
    }

    public String getProvidername()
    {
        return providername;
    }

    public void setProvidername(String providername)
    {
        this.providername = providername;
    }

    public String getGoodsname()
    {
        return goodsname;
    }

    public void setGoodsname(String goodsname)
    {
        this.goodsname = goodsname;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }
}
