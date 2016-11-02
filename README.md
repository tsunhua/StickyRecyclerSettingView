# StickyRecyclerSettingView

Effect 
--------

![](https://github.com/LinLshare/StickyRecyclerSettingView/blob/master/img/effect.gif?raw=true)

Use
--------

#### 1. New a SettingRecyclerAdapter 

```
SettingRecyclerAdapter settingRecyclerAdapter= new SettingRecyclerAdapter();
```

#### 2. Build Setting Group

```
settingRecyclerAdapter.addGroup(
		new SettingHeaderModel(101, "Normal Setting"), // setting header
		new SettingItemModel.Builder("ID") // settingItem 's title
				.secondaryText("2333333", false) // settingItem 's right text
				.build(),
		new SettingItemModel.Builder("Name")
				.secondaryText("Lshare", false)
				.build()
);
settingRecyclerAdapter.addGroup(
		new SettingHeaderModel(104, "Share Setting"),
		new SettingItemModel.Builder("QQ")
				.leftIconRes(R.drawable.ic_qzone) // settingItem 's left icon
				.showSwitch(true) // settingItem if show switch
				.hideRightIcon() // settingItem if hide right arrow icon
				.build()
);
```

#### 3. SetItemClickListener

```
settingRecyclerAdapter.setOnItemClickListener(new SettingRecyclerAdapter.OnItemClickListener() {
	@Override
	public void onItemClick(int position, SettingItemModel settingItemModel, boolean checked) {
		Toast.makeText(this, "position:" + position + "\nswitch check state: " + isChecked, Toast
				.LENGTH_SHORT).show();
	}
});
```

#### 4. RecyclerView Setup

```
recyclerView.setAdapter(settingRecyclerAdapter);
recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(settingRecyclerAdapter);
recyclerView.addItemDecoration(headersDecor); //let header sticky
```

Thanks To
--------

[timehop/sticky-headers-recyclerview](https://github.com/timehop/sticky-headers-recyclerview)
 
License
--------

    Copyright 2016 Lshare

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
