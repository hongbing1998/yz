package org.edu.cdtu.yz.service;

import org.edu.cdtu.yz.bean.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wencheng
 * @since 2019-06-27
 */
public interface IUserService extends IService<User> {

    String login(User user);
}
