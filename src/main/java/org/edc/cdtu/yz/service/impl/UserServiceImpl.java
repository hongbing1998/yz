package org.edc.cdtu.yz.service.impl;

import org.edc.cdtu.yz.bean.User;
import org.edc.cdtu.yz.mapper.UserMapper;
import org.edc.cdtu.yz.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
