package com.techology.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

	/**
	 * 查询总记录数
	 */
	int getCount();

	int getCount(String[] colums, String... parmeters);

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);
	void merge(T entity);
	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(T entity);
	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 按id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(int id);

	/**
	 * 按级别查询 【李成鹏添加】
	 * @param level
	 * @return
	 */
	List<T> getByLevel(int level);
	/**
	 * 按id查询
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Integer[] ids);
	/**
	 * 单表查询 获取列表信息 多重载
	 * 注意 也可以实现多表查询
	 * 
	 * 使用多表查询时  首先查找出联合表的ID 
	 * @param colums//要查询的行 字符串数组类型
	 * @param orderColum//排序字段！！！注意：此字段需要后面追加排序方式 正序asc 倒序desc 例如按照id倒序排序 则字段值应该为 "id desc"
	 * @param parmeters//查询的字段具体数值 与前面的colums对应
	 * @return
	 */
	List<T> get();
	/**
	 * 分页获取所有的实体数据
	 * @param pageIndex从第几条数据开始查找
	 * @param count查询几条数据
	 * @return
	 */
	List<T> get(int pageIndex, int count);
	/**
	 * 根据条件获取实体数据
	 * @param colums string[]数组类型 表示要条件的字段值  与实体字段类型一致
	 * @param orderColum 按照什么字段进行排序 
	 * @param parmeters 条件字段的具体值
	 * @return
	 */
	List<T> get(String[] colums, String orderColum, String... parmeters);
	/**
	 * 根据条件分页获取实体数据
	 * @param pageIndex 从第几条数据开始查找
	 * @param count检索出多少条数据
	 * @param colums string[]数组类型 表示要条件的字段值  与实体字段类型一致
	 * @param orderColum 按照什么字段进行排序 
	 * @param parmeters 条件字段的具体值
	 * @return
	 */
	List<T> get(int pageIndex, int count, String[] colums, String orderColum,
			String... parmeters);
	/**
	 * 教务处分页获取实体数据【李成鹏添加】
	 * @param pageIndex
	 * @param count
	 * @param colums
	 * @param orderColum
	 * @param parmeters
	 * @return
	 */
	List<T> officeGet(int pageIndex, int count, String[] colums, String orderColum,
			String... parmeters);
	/**
	 * 院级以下管理员获取用户角色 【李成鹏添加】
	 * @param colums
	 * @param orderColum
	 * @param parmeters
	 * @return
	 */
	List<T> getUserRols(String[] colums, String orderColum,
			String... parmeters);
	//多表sql语句查询  不推荐使用
	/*List joinGet();*/
	/**
	 *  【去重查询】
	 *  @param field 需要去重的字段
	 *  @param conditions 条件 此方法为 "="的条件 <br> 例:只能为Map的key(字段)=value(数值) <br> 如果没有条件Map为null即可
	 *  @author Andyxq
	 * */
	List<String> getDistinct(String field, Map<String,String> conditions);
	
}