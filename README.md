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

like this:![settings.xml](https://github.com/LinLshare/StickyRecyclerSettingView/blob/master/app/src/main/res/xml/settings.xml)

```java
<settings>
  <!--Normal Setting-->
  <header name="Normal Setting"/>
  <item
      name="ID"
      secondaryTxt="23333"/>
  <item
      name="Name"
      secondaryTxt="Lshare"/>
   ...
</settings>
```

#### 3. SetItemClickListener?

You can do it on ![NormalAdapterDelegate](https://github.com/LinLshare/StickyRecyclerSettingView/blob/master/app/src/main/java/io/github/linlshare/settingstickyrecyclerview/adapter/NormalAdapterDelegate.java) for setting OnclickListener of normal item, or on ![HeaderAdapterDelegate](https://github.com/LinLshare/StickyRecyclerSettingView/blob/master/app/src/main/java/io/github/linlshare/settingstickyrecyclerview/adapter/HeaderAdapterDelegate.java) for header item.

#### 4. RecyclerView Setup

```java
recyclerView.setLayoutManager(
	new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
settingRecyclerAdapter = new SettingRecyclerAdapter();
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
