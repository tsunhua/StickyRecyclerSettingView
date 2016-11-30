# StickyRecyclerSettingView

Effect 
--------

![](https://github.com/LinLshare/StickyRecyclerSettingView/blob/master/img/effect.gif?raw=true)

Use
--------

At first, clone the project and run the demo;
Secondly, copy the code to your project and modify it to adapt your business.

Here are the common way to setup setting view:

#### 1. New a SettingRecyclerAdapter 

```java
SettingRecyclerAdapter settingRecyclerAdapter= new SettingRecyclerAdapter();
```

#### 2. Define Settings.xml

like this: [settings.xml](https://github.com/LinLshare/StickyRecyclerSettingView/blob/master/app/src/main/res/xml/settings.xml)

```xml
<settings xmlns:android="http://schemas.android.com/apk/res/android">
  <header
        name="Normal Setting"/>
  <item
      android:id="@+id/id"
      name="ID"
      secondaryTxt="23333"/>
  <item
      android:id="@+id/name"
      name="Name"
      secondaryTxt="Lshare"/>
   ...
</settings>
```

#### 3. Parse xml file and add data to adapter

use XmlResourceParser to parse the xml file you defined before(like this: [SettingActivity#loadData](https://github.com/LinLshare/StickyRecyclerSettingView/blob/master/app/src/main/java/io/github/linlshare/settingstickyrecyclerview/SettingActivity.java#L54)), and then call `settingRecyclerAdapter.addAll(settingItemList);` to add them to adapter.

#### 4. SetItemClickListener

Just call `settingRecyclerAdapter.setOnItemClickListener(this);` and then handle the event like follow:

```java
@Override public void onItemClick(int id, SettingItem item) {
  Util.toast(this, item);
  if (id == R.id.name) {
    settingRecyclerAdapter.updateSecondaryText(id, "Here it go");
  }
}
```
#### 5. RecyclerView Setup

```java
recyclerView.setLayoutManager(
	new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
recyclerView.setAdapter(settingRecyclerAdapter);
recyclerView.addItemDecoration(new PinnedHeaderItemDecoration());
```

Thanks To
--------

- AdapterDelegates: https://github.com/sockeqwe/AdapterDelegates
- pinned-section-item-decoration: https://github.com/takahr/pinned-section-item-decoration
 
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
