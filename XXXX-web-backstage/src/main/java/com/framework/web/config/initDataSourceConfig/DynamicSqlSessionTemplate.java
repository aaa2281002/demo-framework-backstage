//package com.framework.web.config.initDataSourceConfig;
//
//import org.apache.logging.log4j.LogManager;
//import org.mybatis.spring.SqlSessionTemplate;
//
//import org.apache.ibatis.exceptions.PersistenceException;
//import org.apache.ibatis.executor.BatchResult;
//import org.apache.ibatis.session.*;
//import org.apache.logging.log4j.Logger;
//import org.mybatis.spring.MyBatisExceptionTranslator;
//import org.springframework.dao.support.PersistenceExceptionTranslator;
//import org.springframework.util.Assert;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.util.List;
//import java.util.Map;
//
//import static java.lang.reflect.Proxy.newProxyInstance;
//import static org.apache.ibatis.reflection.ExceptionUtil.unwrapThrowable;
//import static org.mybatis.spring.SqlSessionUtils.*;
//
///**
// * @Author 邋遢龘鵺
// * @ClassName com.framework.web.config.initDataSourceConfig
// * @Description 重写sql会话模板
// * @DateTime 2019/10/11
// * @Version 1.0
// */
//public class DynamicSqlSessionTemplate extends SqlSessionTemplate {
//    private Logger logger = LogManager.getLogger(DynamicSqlSessionTemplate.class);
//    //sql会话工厂
//    private SqlSessionFactory sqlSessionFactory;
//    //执行器枚举类
//    private ExecutorType executorType;
//    //sql会话
//    private SqlSession sqlSessionProxy;
//    //Spring集成与数据访问技术实现的接口
//    private PersistenceExceptionTranslator exceptionTranslator;
//    //多个sql会话工厂对象，用于动态切换数据源
//    private Map<String, SqlSessionFactory> targetSqlSessionFactorys;
//    //默认sql会话
//    private SqlSessionFactory defaultTargetSqlSessionFactory;
//
//    public void setTargetSqlSessionFactorys(Map<String, SqlSessionFactory> targetSqlSessionFactorys) {
//        this.targetSqlSessionFactorys = targetSqlSessionFactorys;
//    }
//
//    public void setDefaultTargetSqlSessionFactory(SqlSessionFactory defaultTargetSqlSessionFactory) {
//        this.defaultTargetSqlSessionFactory = defaultTargetSqlSessionFactory;
//    }
//
//    public DynamicSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        this(sqlSessionFactory, sqlSessionFactory.getConfiguration().getDefaultExecutorType());
//    }
//
//    public DynamicSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
//        this(sqlSessionFactory, executorType, new MyBatisExceptionTranslator(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(), true));
//    }
//
//    public DynamicSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType, PersistenceExceptionTranslator exceptionTranslator) {
//        super(sqlSessionFactory, executorType, exceptionTranslator);
//        this.sqlSessionFactory = sqlSessionFactory;
//        this.executorType = executorType;
//        this.exceptionTranslator = exceptionTranslator;
//        this.sqlSessionProxy = (SqlSession) newProxyInstance(SqlSessionFactory.class.getClassLoader(), new Class[]{SqlSession.class}, new SqlSessionInterceptor());
//        this.defaultTargetSqlSessionFactory = sqlSessionFactory;
//    }
//
//    @Override
//    public SqlSessionFactory getSqlSessionFactory() {
//        //获取当前线程对应注解数据源Key
//        String dataSourceType = DynamicDataSourceHolder.getDataSource();
//        //临时sql会话工厂
//        SqlSessionFactory targetSqlSessionFactory = null;
//        //判断获取TargetDataSource参数列表中dataSource是否有值
//        if (null != dataSourceType) {
//            //有值进入，通过多个sql会话工厂对象，根据Key获取对应的sql会话工厂
//            targetSqlSessionFactory = targetSqlSessionFactorys.get(dataSourceType);
//        }
//        //判断临时sql会话工厂不为空时，直接返回对应的sql会话工厂
//        if (targetSqlSessionFactory != null) {
//            //返回sql会话工厂
//            return targetSqlSessionFactory;
//        } else if (defaultTargetSqlSessionFactory != null) {
//            //defaultTargetSqlSessionFactory 判断默认sql会话工厂不为空时，返回默认sql会话工厂
//            if (dataSourceType != null) {
//                logger.warn("此[" + dataSourceType + "]dataSourceType未配置文件中配置targetSqlSessionFactorys,将会返回defaultTargetSqlSessionFactory来执行后面的操作");
//            }
//            //返回默认sql会话工厂
//            return defaultTargetSqlSessionFactory;
//        } else {
//            Assert.notNull(targetSqlSessionFactorys, "Property 'targetSqlSessionFactorys' or 'defaultTargetSqlSessionFactory' are required");
//            Assert.notNull(defaultTargetSqlSessionFactory, "Property 'defaultTargetSqlSessionFactory' or 'targetSqlSessionFactorys' are required");
//        }
//        //返回初始化默认sql会话工厂
//        return this.sqlSessionFactory;
//    }
//
//    @Override
//    public Configuration getConfiguration() {
//        return this.getSqlSessionFactory().getConfiguration();
//    }
//
//    @Override
//    public ExecutorType getExecutorType() {
//        return this.executorType;
//    }
//
//    @Override
//    public PersistenceExceptionTranslator getPersistenceExceptionTranslator() {
//        return this.exceptionTranslator;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <T> T selectOne(String statement) {
//        return this.sqlSessionProxy.<T>selectOne(statement);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <T> T selectOne(String statement, Object parameter) {
//        return this.sqlSessionProxy.<T>selectOne(statement, parameter);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
//        return this.sqlSessionProxy.<K, V>selectMap(statement, mapKey);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
//        return this.sqlSessionProxy.<K, V>selectMap(statement, parameter, mapKey);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
//        return this.sqlSessionProxy.<K, V>selectMap(statement, parameter, mapKey, rowBounds);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <E> List<E> selectList(String statement) {
//        return this.sqlSessionProxy.<E>selectList(statement);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <E> List<E> selectList(String statement, Object parameter) {
//        return this.sqlSessionProxy.<E>selectList(statement, parameter);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
//        return this.sqlSessionProxy.<E>selectList(statement, parameter, rowBounds);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void select(String statement, ResultHandler handler) {
//        this.sqlSessionProxy.select(statement, handler);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void select(String statement, Object parameter, ResultHandler handler) {
//        this.sqlSessionProxy.select(statement, parameter, handler);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
//        this.sqlSessionProxy.select(statement, parameter, rowBounds, handler);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public int insert(String statement) {
//        return this.sqlSessionProxy.insert(statement);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public int insert(String statement, Object parameter) {
//        return this.sqlSessionProxy.insert(statement, parameter);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public int update(String statement) {
//        return this.sqlSessionProxy.update(statement);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public int update(String statement, Object parameter) {
//        return this.sqlSessionProxy.update(statement, parameter);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public int delete(String statement) {
//        return this.sqlSessionProxy.delete(statement);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public int delete(String statement, Object parameter) {
//        return this.sqlSessionProxy.delete(statement, parameter);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public <T> T getMapper(Class<T> type) {
//        return getConfiguration().getMapper(type, this);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void commit() {
//        throw new UnsupportedOperationException("Manual commit is not allowed over a Spring managed SqlSession");
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void commit(boolean force) {
//        throw new UnsupportedOperationException("Manual commit is not allowed over a Spring managed SqlSession");
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void rollback() {
//        throw new UnsupportedOperationException("Manual rollback is not allowed over a Spring managed SqlSession");
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void rollback(boolean force) {
//        throw new UnsupportedOperationException("Manual rollback is not allowed over a Spring managed SqlSession");
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void close() {
//        throw new UnsupportedOperationException("Manual close is not allowed over a Spring managed SqlSession");
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void clearCache() {
//        this.sqlSessionProxy.clearCache();
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public Connection getConnection() {
//        return this.sqlSessionProxy.getConnection();
//    }
//
//    /**
//     * {@inheritDoc}
//     *
//     * @since 1.0.2
//     */
//    @Override
//    public List<BatchResult> flushStatements() {
//        return this.sqlSessionProxy.flushStatements();
//    }
//
//    /**
//     * Proxy needed to route MyBatis method calls to the proper SqlSession got from Spring's Transaction Manager It also unwraps exceptions thrown by
//     * {@code Method#invoke(Object, Object...)} to pass a {@code PersistenceException} to the {@code PersistenceExceptionTranslator}.
//     */
//    private class SqlSessionInterceptor implements InvocationHandler {
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            SqlSession sqlSession = getSqlSession(
//                    DynamicSqlSessionTemplate.this.getSqlSessionFactory(),
//                    DynamicSqlSessionTemplate.this.executorType,
//                    DynamicSqlSessionTemplate.this.exceptionTranslator);
//            try {
//                Object result = method.invoke(sqlSession, args);
//                if (!isSqlSessionTransactional(sqlSession, DynamicSqlSessionTemplate.this.getSqlSessionFactory())) {
//                    // force commit even on non-dirty sessions because some databases require / a commit/rollback before calling close()
//                    sqlSession.commit(true);
//                }
//                return result;
//            } catch (Throwable t) {
//                Throwable unwrapped = unwrapThrowable(t);
//                if (DynamicSqlSessionTemplate.this.exceptionTranslator != null && unwrapped instanceof PersistenceException) {
//                    // release the connection to avoid a deadlock if the translator is no loaded. See issue #22
//                    closeSqlSession(sqlSession, DynamicSqlSessionTemplate.this.getSqlSessionFactory());
//                    sqlSession = null;
//                    Throwable translated = DynamicSqlSessionTemplate.this.exceptionTranslator.translateExceptionIfPossible((PersistenceException) unwrapped);
//                    if (translated != null) {
//                        unwrapped = translated;
//                    }
//                }
//                throw unwrapped;
//            } finally {
//                if (sqlSession != null) {
//                    closeSqlSession(sqlSession, DynamicSqlSessionTemplate.this.getSqlSessionFactory());
//                }
//            }
//        }
//    }
//}
