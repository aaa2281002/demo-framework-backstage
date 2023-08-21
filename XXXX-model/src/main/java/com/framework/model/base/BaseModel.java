package com.framework.model.base;

import com.framework.common.annotation.QueryParam;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.date.DateStyleUtil;
import com.framework.common.util.other.NumeralUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.model.base
 * @description 公共实体类，一些通用的实体类变量全部申明在里面，用于其他实体类集成
 * @datetime 2019/10/11
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = -1L;
    //编号
    @NotNull(message = "请选择信息", groups = {ValidationGroup.formEdit.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "信息不存在", groups = {ValidationGroup.formEdit.class})
    private Long id;
    //创建账户ID
    private Long createId;
    //创建账户名称
    @QueryParam(code = "cuser.LOGIN_NAME")
    private String createUserName;
    //创建日期时间
//    @JsonFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS, timezone = DateStyleUtil.STRING_CHINA_TIMEZONE)
    @DateTimeFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)
    @QueryParam(code = "p.CREATE_TIME", isDefault = true)
    private Date createTime;
    //创建日期时间字符串
    private String createTimeStr;
    //操作账户ID
    private Long operaterId;
    //操作账户名称
    @QueryParam(code = "ouser.LOGIN_NAME")
    private String operaterUserName;
    //操作日期时间
//    @JsonFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS, timezone = DateStyleUtil.STRING_CHINA_TIMEZONE)
    @DateTimeFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)
    @QueryParam(code = "p.OPERATER_TIME")
    private Date operaterTime;
    //操作日期时间字符串
    private String operaterTimeStr;
    //操作状态 -1=删除， 1=新增, 2=修改
    @QueryParam(code = "p.OPERATER_STATUS")
    private Integer operaterStatus;
    //操作状态 大于等于 -1=删除， 1=新增, 2=修改
    private Integer gtaeOperaterStatus;
    //操作状态 小于等于 -1=删除， 1=新增, 2=修改
    private Integer ltaeOperaterStatus;
    //大于等于
    private Integer gtaeNum;
    //大于
    private Integer gtNum;
    //小于等于
    private Integer ltaeNum;
    //小于
    private Integer ltNum;
    //树形菜单搜索关键字
    private String keyword;
    //模糊搜索类型
    private String fuzzyType;
    //模糊搜索值
    private String fuzzyValue;
    //搜索开始时间
    @DateTimeFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)
//    @JsonFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS, timezone = DateStyleUtil.STRING_CHINA_TIMEZONE)
    private Date startTime;
    //搜索结束时间
    @DateTimeFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)
//    @JsonFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS, timezone = DateStyleUtil.STRING_CHINA_TIMEZONE)
    private Date endTime;
    //排序字段
    private String sort;
    //正序，倒序
    private String order;
    //搜索条件
    private String search;
    //页码
    @NotNull(message = "页码不能为空", groups = {ValidationGroup.formPageQuery.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "页码不能小于0", groups = {ValidationGroup.formPageQuery.class})
    @Max(value = Integer.MAX_VALUE, message = "页码不能大于" + Integer.MAX_VALUE, groups = {ValidationGroup.formPageQuery.class})
    private Integer page;
    //mysql分页起始条数
    private Integer offset;
    //每页显示数量
    @NotNull(message = "每页数量不能为空", groups = {ValidationGroup.formPageQuery.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "每页数量不能小于0", groups = {ValidationGroup.formPageQuery.class})
    @Max(value = NumeralUtil.POSITIVE_SIX_HUNDRED, message = "每页数量不能大于" + Integer.MAX_VALUE, groups = {ValidationGroup.formPageQuery.class})
    private Integer limit;
    //ID集合
    private List<Long> idList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr == null ? null : createTimeStr.trim();
    }

    public Long getOperaterId() {
        return operaterId;
    }

    public void setOperaterId(Long operaterId) {
        this.operaterId = operaterId;
    }

    public String getOperaterUserName() {
        return operaterUserName;
    }

    public void setOperaterUserName(String operaterUserName) {
        this.operaterUserName = operaterUserName == null ? null : operaterUserName.trim();
    }

    public Date getOperaterTime() {
        return operaterTime;
    }

    public void setOperaterTime(Date operaterTime) {
        this.operaterTime = operaterTime;
    }

    public String getOperaterTimeStr() {
        return operaterTimeStr;
    }

    public void setOperaterTimeStr(String operaterTimeStr) {
        this.operaterTimeStr = operaterTimeStr == null ? null : operaterTimeStr.trim();
    }

    public Integer getOperaterStatus() {
        return operaterStatus;
    }

    public void setOperaterStatus(Integer operaterStatus) {
        this.operaterStatus = operaterStatus;
    }

    public Integer getGtaeOperaterStatus() {
        return gtaeOperaterStatus;
    }

    public void setGtaeOperaterStatus(Integer gtaeOperaterStatus) {
        this.gtaeOperaterStatus = gtaeOperaterStatus;
    }

    public Integer getLtaeOperaterStatus() {
        return ltaeOperaterStatus;
    }

    public void setLtaeOperaterStatus(Integer ltaeOperaterStatus) {
        this.ltaeOperaterStatus = ltaeOperaterStatus;
    }

    public Integer getGtaeNum() {
        return gtaeNum;
    }

    public void setGtaeNum(Integer gtaeNum) {
        this.gtaeNum = gtaeNum;
    }

    public Integer getGtNum() {
        return gtNum;
    }

    public void setGtNum(Integer gtNum) {
        this.gtNum = gtNum;
    }

    public Integer getLtaeNum() {
        return ltaeNum;
    }

    public void setLtaeNum(Integer ltaeNum) {
        this.ltaeNum = ltaeNum;
    }

    public Integer getLtNum() {
        return ltNum;
    }

    public void setLtNum(Integer ltNum) {
        this.ltNum = ltNum;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getFuzzyType() {
        return fuzzyType;
    }

    public void setFuzzyType(String fuzzyType) {
        this.fuzzyType = fuzzyType == null ? null : fuzzyType.trim();
    }

    public String getFuzzyValue() {
        return fuzzyValue;
    }

    public void setFuzzyValue(String fuzzyValue) {
        this.fuzzyValue = fuzzyValue == null ? null : fuzzyValue.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order == null ? null : order.trim();
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search == null ? null : search.trim();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    //处理分页页数
    public Integer getOffset() {
        if (this.page == null) {
            return this.page;
        }
        return (this.page - NumeralUtil.POSITIVE_ONE) * this.limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}

