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
@TableName("bus_outport")
public class Outport implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer providerid;

    private String paytype;

    private Date outputtime;

    private String operateperson;

    private Double outportprice;

    private Integer number;

    private String remark;

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

    public Integer getProviderid()
    {
        return providerid;
    }

    public void setProviderid(Integer providerid)
    {
        this.providerid = providerid;
    }

    public String getPaytype()
    {
        return paytype;
    }

    public void setPaytype(String paytype)
    {
        this.paytype = paytype;
    }

    public Date getOutputtime()
    {
        return outputtime;
    }

    public void setOutputtime(Date outputtime)
    {
        this.outputtime = outputtime;
    }

    public String getOperateperson()
    {
        return operateperson;
    }

    public void setOperateperson(String operateperson)
    {
        this.operateperson = operateperson;
    }

    public Double getOutportprice()
    {
        return outportprice;
    }

    public void setOutportprice(Double outportprice)
    {
        this.outportprice = outportprice;
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
