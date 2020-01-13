package com.framework.service.service.system;

import com.framework.common.response.ResponseResult;
import com.framework.model.entity.system.SystemButton;

import java.util.List;
import java.util.Map;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.service.system
 * @Description 按钮业务接口类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public interface SystemButtonService {

    /**
     * @param record 1 按钮实体类对象
     * @return int 大于等于1成功， 0失败
     * @Titel 公共根据条件逻辑删除数据
     * @Description 公共根据条件逻辑删除数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:04
     */
    int deleteList(SystemButton record);

    /**
     * @param record 1 按钮实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共添加
     * @Description 公共添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insert(SystemButton record);

    /**
     * @param record 1 按钮实体类对象
     * @return long 返回主键ID大于0成功，小于等于0失败
     * @Titel 公共非空字段验证添加
     * @Description 公共非空字段验证添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:31
     */
    long insertSelective(SystemButton record);

    /**
     * @param list 1 按钮批量添加集合
     * @return int 返回成功数量
     * @Titel 公共批量添加
     * @Description 公共批量添加
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 14:12
     */
    int insertList(List<SystemButton> list);

    /**
     * @param record 1 按钮实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号修改
     * @Description 公共根据编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKey(SystemButton record);

    /**
     * @param record 1 按钮实体类对象
     * @return int 0失败，1成功
     * @Titel 公共根据编号非空字段验证修改
     * @Description 公共根据编号非空字段验证修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:35
     */
    int updateByPrimaryKeySelective(SystemButton record);


    /**
     * @param list 1 按钮批量修改集合
     * @return int 返回成功数量
     * @Titel 公共批量根据非空验证编号修改
     * @Description 公共批量根据非空验证编号修改
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:59
     */
    int updateList(List<SystemButton> list);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemButton
     * @Titel 公共根据编号查询数据
     * @Description 公共根据编号查询数据
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:33
     */
    SystemButton selectByPrimaryKey(Long id);

    /**
     * @param record 1 按钮实体类对象
     * @return int 0不存在， 大于等于1存在
     * @Titel 公共根据条件查询是否重复存在
     * @Description 公共根据条件查询是否重复存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:34
     */
    int isExist(SystemButton record);

    /**
     * @param record 1 按钮实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemButton>
     * @Titel 公共分页查询
     * @Description 公共分页查询
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    List<SystemButton> findPageList(SystemButton record);

    /**
     * @param record 1 按钮实体类对象
     * @return int 返回分页总数
     * @Titel 公共分页查询总数
     * @Description 公共分页查询总数
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 9:41
     */
    int findPageListCount(SystemButton record);

    /**
     * @param record 1 按钮实体类对象
     * @return java.util.List<com.framework.model.entity.system.SystemButton>
     * @Titel 公共根据条件查询集合
     * @Description 公共根据条件查询集合
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:01
     */
    List<SystemButton> findByList(SystemButton record);

    /**
     * @param record 1 按钮实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理添加方法
     * @Description 本类后台管理添加方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult save(SystemButton record);

    /**
     * @param record 1 按钮实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理修改方法
     * @Description 本类后台管理修改方法
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:13
     */
    public ResponseResult edit(SystemButton record);

    /**
     * @param idList 1 编号集合
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理批量逻辑删除
     * @Description 本类后台管理批量逻辑删除
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    public ResponseResult batchDeleteList(List<Long> idList);

    /**
     * @param id         1 编号
     * @param buttonCode 2 按钮代码
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理查询是否存在，只是适合本身的一些方法处理。
     * @Description boolean false不存在， true存在
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:14
     */
    ResponseResult isExist(Long id, String buttonCode);

    /**
     * @param id 1 编号
     * @return com.framework.model.entity.system.SystemButton
     * @Titel 本类后台管理根据ID查询数据信息
     * @Description 本类后台管理根据ID查询数据信息
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:16
     */
    SystemButton getByIdParam(Long id);

    /**
     * @param param 1 按钮实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 本类后台管理根据条件分页查询按钮列表
     * @Description 本类后台管理根据条件分页查询按钮列表
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 11:20
     */
    ResponseResult findParamPageList(SystemButton param);

    /**
     * @param param 1 按钮实体类对象
     * @return com.framework.common.response.ResponseResult
     * @Titel 根据条件查询按钮列表, 用于角色，菜单，按钮关联表格
     * @Description 根据条件查询按钮列表, 用于角色，菜单，按钮关联表格
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/26 13:52
     */
    ResponseResult findParamList(SystemButton param);
}
