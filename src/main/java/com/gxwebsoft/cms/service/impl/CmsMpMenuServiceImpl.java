package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsMpMenuMapper;
import com.gxwebsoft.cms.service.CmsMpMenuService;
import com.gxwebsoft.cms.entity.CmsMpMenu;
import com.gxwebsoft.cms.param.CmsMpMenuParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小程序端菜单Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsMpMenuServiceImpl extends ServiceImpl<CmsMpMenuMapper, CmsMpMenu> implements CmsMpMenuService {

    @Override
    public PageResult<CmsMpMenu> pageRel(CmsMpMenuParam param) {
        PageParam<CmsMpMenu, CmsMpMenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsMpMenu> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsMpMenu> listRel(CmsMpMenuParam param) {
        List<CmsMpMenu> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsMpMenu, CmsMpMenuParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsMpMenu getByIdRel(Integer menuId) {
        CmsMpMenuParam param = new CmsMpMenuParam();
        param.setMenuId(menuId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
