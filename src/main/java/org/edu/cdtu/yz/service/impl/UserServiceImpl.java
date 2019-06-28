package org.edu.cdtu.yz.service.impl;

import org.edu.cdtu.yz.bean.User;
import org.edu.cdtu.yz.mapper.UserMapper;
import org.edu.cdtu.yz.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public String login(User user) {
        User result = baseMapper.selectOne(user);
        if (result == null || !result.getPassword().equals(user.getPassword())) {
            return "登录失败";
        } else {
            return "登录成功";
        }
    }
}
