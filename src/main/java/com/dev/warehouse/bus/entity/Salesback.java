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
@TableName("bus_salesback")
public class Salesback implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer customerid;

    private String paytype;

    private Date salesbacktime;

    private Double salebackprice;

    private String operateperson;

    private Integer number;

    private String remark;

    private Integer goodsid;

    /**
     * 客户姓名
     */
    @TableField(exist = false)
    private String customername;

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

    public Integer getCustomerid()
    {
        return customerid;
    }

    public void setCustomerid(Integer customerid)
    {
        this.customerid = customerid;
    }

    public String getPaytype()
    {
        return paytype;
    }

    public void setPaytype(String paytype)
    {
        this.paytype = paytype;
    }

    public Date getSalesbacktime()
    {
        return salesbacktime;
    }

    public void setSalesbacktime(Date salesbacktime)
    {
        this.salesbacktime = salesbacktime;
    }

    public Double getSalebackprice()
    {
        return salebackprice;
    }

    public void setSalebackprice(Double salebackprice)
    {
        this.salebackprice = salebackprice;
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

    public Integer getGoodsid()
    {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid)
    {
        this.goodsid = goodsid;
    }

    public String getCustomername()
    {
        return customername;
    }

    public void setCustomername(String customername)
    {
        this.customername = customername;
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
