package io.seata.samples.dubbo.myproblem;

import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author weijingjing
 * @date 2019-07-02
 */
public class ScmDtxRemoteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScmDtxRemoteService.class);

    private ScmDtxDao scmDtxDao;

    @GlobalTransactional(timeoutMills = 3000000, name = "htw-bhScm-bindLock-gtx")
    public boolean addLockDtx(ScmDtxDO scmDtxDO) {
        try {
            int rows = scmDtxDao.add(scmDtxDO);
            boolean success = rows > 0;
            if (!success) {
                throw new RuntimeException("db failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return true;
    }

    public boolean addLock(ScmDtxDO scmDtxDO) {
        try {
            int rows = scmDtxDao.add(scmDtxDO);
            boolean success = rows > 0;
            if (!success) {
                throw new RuntimeException("db failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return true;
    }

    public void setScmDtxDao(ScmDtxDao scmDtxDao) {
        this.scmDtxDao = scmDtxDao;
    }

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"myproblem/seata-dal.xml"});
            ScmDtxRemoteService service = (ScmDtxRemoteService) ctx.getBean("dtxRemoteService");

            ScmDtxDO scmDtxDO = new ScmDtxDO();
            scmDtxDO.setLockId(12345678L);
            scmDtxDO.setLockName("dtx-lock");
            boolean result = service.addLock(scmDtxDO);
            LOGGER.info("dtx result={}",
                    new Object[] {result});

            scmDtxDO.setLockId(12345679L);
            result = service.addLockDtx(scmDtxDO);
            LOGGER.info("local result={}",
                    new Object[] {result});
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
