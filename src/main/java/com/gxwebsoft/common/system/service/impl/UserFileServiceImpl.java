package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.system.mapper.UserFileMapper;
import com.gxwebsoft.common.system.service.UserFileService;
import com.gxwebsoft.common.system.entity.UserFile;
import org.springframework.stereotype.Service;

/**
 * 用户文件Service实现
 *
 * @author WebSoft
 * @since 2022-07-21 14:34:40
 */
@Service
public class UserFileServiceImpl extends ServiceImpl<UserFileMapper, UserFile> implements UserFileService {

}
