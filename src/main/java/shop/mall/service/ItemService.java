package shop.mall.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.mall.entity.Item;
import shop.mall.repository.ItemRepository;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItem(Item item, MultipartFile imgFile) throws Exception {
        String oriImgName = imgFile.getOriginalFilename();
        String imgName = "";
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/";

        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid + "_" + oriImgName;
        imgName = saveFileName;
        File saveFile = new File(projectPath, imgName);
        imgFile.transferTo(saveFile);

        item.setImgName(imgName);
        item.setImgPath("/files/" + imgName);

        itemRepository.save(item);
    }

    public Item itemView(Long id) {
        return itemRepository.findById(id).get();
    }

    public List<Item> allItemView() {
        return itemRepository.findAll();
    }

    public void itemModify(Item item, Long id) {
        Item update = itemRepository.findById(id).get();
        update.setName(item.getName());
        update.setText(item.getText());
        update.setPrice(item.getPrice());
        update.setStock(item.getStock());
        itemRepository.save(update);
    }

    public void itemDelete(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> userItem(Long id) {
        System.out.println("itemRepository.findByUserId(id)" + itemRepository.findByUserId(id));
        return itemRepository.findByUserId(id);
    }

}
