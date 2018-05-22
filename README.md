# CountrySelectorEditView
带有下拉选择的EditView

项目当中需要用的一个带国家选择还要带输入框的EditView,并且点击的时候还有文字颜色的变化,简单的写了一个.

首先是默认状态,username为黑色,外框为蓝色
![](https://raw.githubusercontent.com/BestWhy/CountrySelectorEditView/master/Screenshots/000.jpg)

当EditView失去焦点的时候,username颜色变成灰色,外框也变成灰色.
![](https://raw.githubusercontent.com/BestWhy/CountrySelectorEditView/master/Screenshots/001.jpg)

最后就是国家选择了
![](https://raw.githubusercontent.com/BestWhy/CountrySelectorEditView/master/Screenshots/002.jpg)

添加右侧drawable,可以清除输入内容
![](https://raw.githubusercontent.com/BestWhy/CountrySelectorEditView/master/Screenshots/003.jpg)

>具体用法
```
<view.CountrySelectorEditView
  app:drawableRight="@mipmap/icon_clear"
  android:id="@+id/country_selector"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  app:countrySelectorMode="wrap_content"
  app:hint="@string/in_put_phone_str" />
```

```
private void initView() {
  mCountrySelector = findViewById(R.id.country_selector);
  for (int i = 0; i < 10; i++) {
      Country country = new Country();
      if (i % 2 == 0) {
          country.countryCode = "+44";
          country.countryName = "GBA";

      } else {
          country.countryCode = "+31";
          country.countryName = "FRA";
      }
      mArrayList.add(country);
  }
  //Adapter 就是基本的ListViewAdapter
  CountrySelectorAdapter adapter = new CountrySelectorAdapter(this, mArrayList);
  mCountrySelector.setCountryListAdapter(adapter);
}
```

