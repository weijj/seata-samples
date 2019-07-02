package io.seata.samples.dubbo.myproblem;

import org.apache.ibatis.session.SqlSession;

/**
 * @author weijingjing
 * @date 2019-07-02
 */
public class ScmDtxDaoImpl implements ScmDtxDao {
    private SqlSession scmSqlSession;

    public void setScmSqlSession(SqlSession scmSqlSession) {
        this.scmSqlSession = scmSqlSession;
    }

    @Override
    public int add(ScmDtxDO scmDtxDO) {
        return scmSqlSession.insert("ScmDtxDO.insert", scmDtxDO);
    }
}