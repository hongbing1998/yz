package org.edu.cdtu.yz.service.impl;

import org.edu.cdtu.yz.bean.Work;
import org.edu.cdtu.yz.mapper.WorkMapper;
import org.edu.cdtu.yz.service.IWorkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-26
 */
@Service
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements IWorkService {

}
