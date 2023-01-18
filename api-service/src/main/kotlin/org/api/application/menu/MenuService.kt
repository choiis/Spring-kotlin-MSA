package org.api.application.menu

import org.api.entity.menu.MenuEntity
import org.api.entity.menu.MenuRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    fun getMenuByRid(rid: String, page: Pageable): Page<MenuResponse> {
        val entityPage: Page<MenuEntity> = menuRepository.findByRestaurant_Rid(rid, page);
        val responsePage = entityPage.map { entity -> MenuClassUtils.entityToResponse(entity) };
        return responsePage;
    }

    @Transactional
    fun removeMenuOne(mid: String) {
        var vo: MenuEntity = MenuEntity();
        vo.mid = mid;
        menuRepository.delete(vo)
        return;
    }
}