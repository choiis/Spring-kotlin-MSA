package org.api.application.menu

import org.api.entity.menu.MenuEntity
import org.api.entity.menu.MenuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MenuService {

    @Autowired
    private lateinit var menuRepository: MenuRepository

    @Transactional
    fun saveMenu(menu: MenuRequest): MenuResponse? {
        val entity: MenuEntity = menuRepository.save(MenuClassUtils.requestToEntity(menu));
        return MenuClassUtils.entityToResponse(entity)
    }

    @Transactional(readOnly = true)
    fun getMenuOne(mid: String): MenuResponse? {

        val option: Optional<MenuEntity> = menuRepository.findByMid(mid);
        return if (option.isPresent) {
            val entity = option.get()
            MenuClassUtils.entityToResponse(entity)
        } else {
            null;
        }
    }


    @Transactional(readOnly = true)
    fun getMenuByRid(rid: String): List<MenuResponse> {
        val list = menuRepository.findByRestaurant_Rid(rid)
        val menuList: ArrayList<MenuResponse> = arrayListOf<MenuResponse>();
        for (entity in list) {
            menuList.add(MenuClassUtils.entityToResponse(entity))
        }
        return menuList;
    }

    @Transactional
    fun removeMenuOne(mid: String) {
        var vo: MenuEntity = MenuEntity();
        vo.mid = mid;
        menuRepository.delete(vo)
        return;
    }
}