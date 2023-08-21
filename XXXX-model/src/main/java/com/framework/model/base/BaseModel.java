package com.framework.model.base;

import com.framework.common.annotation.QueryParam;
import com.framework.common.model.validation.ValidationGroup;
import com.framework.common.util.date.DateStyleUtil;
import com.framework.common.util.other.NumeralUtil;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "编号", name = "id", dataType = "Long", required = false, allowableValues = "20", hidden = false, example = "1-" + Long.MAX_VALUE, position = 0)
    @NotNull(message = "请选择信息", groups = {ValidationGroup.formEdit.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "信息不存在", groups = {ValidationGroup.formEdit.class})
    private Long id;
    //创建账户编号
    @ApiModelProperty(value = "创建账户编号", name = "createId", dataType = "Long", required = false, allowableValues = "20", hidden = true, example = "1-" + Long.MAX_VALUE)
    private Long createId;
    //创建账户名称
    @QueryParam(code = "cuser.LOGIN_NAME")
    @ApiModelProperty(value = "创建账户名称", name = "createUserName", dataType = "String", required = false, allowableValues = "32", hidden = true, example = "xxx")
    private String createUserName;
    //创建日期时间
    @DateTimeFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)
    @QueryParam(code = "p.CREATE_TIME", isDefault = true)
    @ApiModelProperty(value = "创建日期时间", name = "createTime", dataType = "Date", required = false, hidden = true, example = "2023-01-01 00:00:00")
    private Date createTime;
    //创建日期时间字符串
    @ApiModelProperty(value = "创建日期时间字符串", name = "createTimeStr", dataType = "String", required = false, allowableValues = "32", hidden = true, example = "2023-01-01 00:00:00")
    private String createTimeStr;
    //操作账户编号
    @ApiModelProperty(value = "操作账户编号", name = "operaterId", dataType = "Long", required = false, allowableValues = "20", hidden = true, example = "1-" + Long.MAX_VALUE)
    private Long operaterId;
    //操作账户名称
    @QueryParam(code = "ouser.LOGIN_NAME")
    @ApiModelProperty(value = "操作账户名称", name = "operaterUserName", dataType = "String", required = false, allowableValues = "32", hidden = true, example = "xxxx")
    private String operaterUserName;
    //操作日期时间
    @DateTimeFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)
    @QueryParam(code = "p.OPERATER_TIME")
    @ApiModelProperty(value = "操作日期时间", name = "operaterTime", dataType = "Date", required = false, hidden = true, example = "2023-01-01 00:00:00")
    private Date operaterTime;
    //操作日期时间字符串
    @ApiModelProperty(value = "操作日期时间字符串", name = "operaterTimeStr", dataType = "String", required = false, allowableValues = "32", hidden = true, example = "2023-01-01 00:00:00")
    private String operaterTimeStr;
    //操作状态 -1=删除， 1=新增, 2=修改
    @QueryParam(code = "p.OPERATER_STATUS")
    @ApiModelProperty(value = "操作状态 -1=删除， 1=新增, 2=修改", name = "operaterStatus", dataType = "Integer", required = false, allowableValues = "1", hidden = true)
    private Integer operaterStatus;
    //操作状态 大于等于 -1=删除， 1=新增, 2=修改
    @ApiModelProperty(hidden = true)
    private Integer gtaeOperaterStatus;
    //操作状态 小于等于 -1=删除， 1=新增, 2=修改
    @ApiModelProperty(hidden = true)
    private Integer ltaeOperaterStatus;
    //大于等于
    @ApiModelProperty(value = "大于等于", name = "gtaeNum", dataType = "Integer", required = false, allowableValues = "11", hidden = true, example = Integer.MIN_VALUE + "-" + Integer.MAX_VALUE)
    private Integer gtaeNum;
    //大于
    @ApiModelProperty(value = "大于", name = "gtNum", dataType = "Integer", required = false, allowableValues = "11", hidden = true, example = Integer.MIN_VALUE + "-" + Integer.MAX_VALUE)
    private Integer gtNum;
    //小于等于
    @ApiModelProperty(value = "小于等于", name = "ltaeNum", dataType = "Integer", required = false, allowableValues = "11", hidden = true, example = Integer.MIN_VALUE + "-" + Integer.MAX_VALUE)
    private Integer ltaeNum;
    //小于
    @ApiModelProperty(value = "小于", name = "ltNum", dataType = "Integer", required = false, allowableValues = "11", hidden = true, example = Integer.MIN_VALUE + "-" + Integer.MAX_VALUE)
    private Integer ltNum;
    //树形菜单搜索关键字
    @ApiModelProperty(value = "树形菜单搜索关键字", name = "keyword", dataType = "String", required = false, allowableValues = "255", hidden = true, example = "xxx")
    private String keyword;
    //模糊搜索类型
    @ApiModelProperty(value = "模糊搜索类型", name = "fuzzyType", dataType = "String", required = false, allowableValues = "255", hidden = true, example = "xxx")
    private String fuzzyType;
    //模糊搜索值
    @ApiModelProperty(value = "模糊搜索值", name = "fuzzyValue", dataType = "String", required = false, allowableValues = "255", hidden = true, example = "xxx")
    private String fuzzyValue;
    //搜索开始时间
    @DateTimeFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)
    @ApiModelProperty(value = "搜索开始时间", name = "startTime", dataType = "Date", required = false, hidden = true, example = "2023-01-01 00:00:00")
    private Date startTime;
    //搜索结束时间
    @DateTimeFormat(pattern = DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS)
    @ApiModelProperty(value = "搜索结束时间", name = "endTime", dataType = "Date", required = false, hidden = true, example = "2023-01-30 23:59:59")
    private Date endTime;
    //排序字段
    @ApiModelProperty(value = "排序字段", name = "sort", dataType = "String", allowableValues = "255", required = false, hidden = true, example = "x.xxx")
    private String sort;
    //正序，倒序
    @ApiModelProperty(value = "正序，倒序", name = "order", dataType = "String", allowableValues = "32", required = false, hidden = true, example = "desc|asc")
    private String order;
    //搜索条件
    @ApiModelProperty(value = "搜索条件", name = "search", dataType = "String", allowableValues = "255", required = false, hidden = true, example = "xxx")
    private String search;
    //页码
    @NotNull(message = "页码不能为空", groups = {ValidationGroup.formPageQuery.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "页码不能小于0", groups = {ValidationGroup.formPageQuery.class})
    @Max(value = Integer.MAX_VALUE, message = "页码不能大于" + Integer.MAX_VALUE, groups = {ValidationGroup.formPageQuery.class})
    @ApiModelProperty(value = "页码", name = "page", dataType = "Integer", allowableValues = "11", required = false, hidden = true, example = "1")
    private Integer page;
    //mysql分页起始条数
    @ApiModelProperty(value = "分页条数", name = "offset", dataType = "Integer", allowableValues = "11", required = false, hidden = true, example = "1")
    private Integer offset;
    //每页显示数量
    @NotNull(message = "每页数量不能为空", groups = {ValidationGroup.formPageQuery.class})
    @Min(value = NumeralUtil.POSITIVE_ONE, message = "每页数量不能小于0", groups = {ValidationGroup.formPageQuery.class})
    @Max(value = NumeralUtil.POSITIVE_SIX_HUNDRED, message = "每页数量不能大于" + Integer.MAX_VALUE, groups = {ValidationGroup.formPageQuery.class})
    @ApiModelProperty(value = "每页显示数量", name = "limit", dataType = "Integer", allowableValues = "11", required = false, hidden = true, example = "10")
    private Integer limit;
    //ID集合
    @ApiModelProperty(value = "ID集合", name = "idList", dataType = "Integer", allowableValues = "100", required = false, hidden = true, example = "[]")
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

