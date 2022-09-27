package org.api.menu

import org.api.restaurant.RestaurantEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MenuService {

    @Autowired
    private lateinit var menuRepository: MenuRepository

    fun saveMenu(menu: MenuRequest) : MenuResponse? {
        var saveEntity: MenuEntity = MenuEntity()
        saveEntity.restaurant = RestaurantEntity()
        saveEntity.restaurant!!.rid = menu.rid
        saveEntity.name = menu.name
        saveEntity.star = menu.star
        saveEntity.cost = menu.cost
        var entity: MenuEntity = menuRepository.save(saveEntity!!);
        return entity.restaurant?.let { MenuResponse(entity.mid, it.rid,entity.name,entity.star, entity.cost) }
    }

    fun getMenuOne(mid: String) : MenuResponse? {

        var option: Optional<MenuEntity> = menuRepository.findByMid(mid);
        if(option.isPresent) {
            var entity = option.get()
            return MenuResponse(entity.mid,entity.restaurant!!.rid,entity.name,entity.star, entity.cost)
        } else {
            return null;
        }
    }


    fun getMenuByRid(rid: Int) : List<MenuResponse> {
       var list = menuRepository.findByRestaurant_Rid(rid)
        var menuList = arrayListOf<MenuResponse>();
        for (entity in list) {
            if (entity != null) {
                menuList.add(MenuResponse(entity.mid,entity.restaurant!!.rid,entity.name,entity.star, entity.cost))
            }
        }
        return menuList;
    }

    fun removeMenuOne(mid:String) {
        var vo: MenuEntity = MenuEntity();
        vo.mid = mid;
        menuRepository.delete(vo)
        return;
    }
}