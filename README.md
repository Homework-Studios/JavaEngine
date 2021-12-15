# Engine
My Plugin Engine


Things i have to add later
  - [ ] CabelAPI
  - [ ] ScorebordAPI
  - [ ] TablistAPI
  - [x] Readme



How to use ScrollableInventoryAPI
```java
//player is the player
//test is the name
//size is the size
//page is the page player is starting
//pages is number of pages if left null pages are integerlimit
//contents is a List<ItemStack>
ScrollableInventory scrollable_inventory = new ScrollableInventory().create(player,"test",size,page,pages);
scrollable_inventory.setContents(contents);
scrollable_inventory.reloadInventory();
```
