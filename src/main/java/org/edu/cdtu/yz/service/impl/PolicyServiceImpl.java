package org.edu.cdtu.yz.service.impl;

import org.edu.cdtu.yz.bean.Policy;
import org.edu.cdtu.yz.mapper.PolicyMapper;
import org.edu.cdtu.yz.service.IPolicyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
@Service
public class PolicyServiceImpl extends ServiceImpl<PolicyMapper, Policy> implements IPolicyService {

}
