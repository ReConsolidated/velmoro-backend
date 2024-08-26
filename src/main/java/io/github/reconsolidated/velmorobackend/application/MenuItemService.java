package io.github.reconsolidated.velmorobackend.application;

import io.github.reconsolidated.velmorobackend.domain.menuItem.MenuItem;
import io.github.reconsolidated.velmorobackend.domain.menuItem.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getAllMenuItems(String urlName) {
        if (urlName != null) {
            return menuItemRepository.findByActiveTrueAndHotelUrlName(urlName, Sort.by("priority"));
        }
        return menuItemRepository.findByActiveTrue(Sort.by("priority"));
    }

    public Optional<MenuItem> getMenuItemById(Long id) {
        return menuItemRepository.findById(id);
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItemDetails) {
        return menuItemRepository.findById(id)
                .map(menuItem -> {
                    menuItem.setHotel(menuItemDetails.getHotel());
                    menuItem.setCategoryId(menuItemDetails.getCategoryId());
                    menuItem.setDisplayName(menuItemDetails.getDisplayName());
                    menuItem.setDisplayNameEN(menuItemDetails.getDisplayNameEN());
                    menuItem.setDisplayNameDE(menuItemDetails.getDisplayNameDE());
                    menuItem.setImageUrl(menuItemDetails.getImageUrl());
                    menuItem.setPrice(menuItemDetails.getPrice());
                    menuItem.setDescription(menuItemDetails.getDescription());
                    menuItem.setDescriptionEN(menuItemDetails.getDescriptionEN());
                    menuItem.setDescriptionDE(menuItemDetails.getDescriptionDE());
                    menuItem.setActive(menuItemDetails.getActive());
                    menuItem.setPriority(menuItemDetails.getPriority());
                    return menuItemRepository.save(menuItem);
                })
                .orElseThrow(() -> new RuntimeException("MenuItem not found with id " + id));
    }

    public void deleteMenuItem(Long id) {
        MenuItem item = menuItemRepository.findById(id).orElseThrow();
        item.setActive(false);
        menuItemRepository.save(item);
    }
}

