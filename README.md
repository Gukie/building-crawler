# building-crawler

This project will be made as a micro-service to another project named 'spring-cloud'

****
This project is mainly for praticing multiple-thread in Java.
***
refer: https://www.ibm.com/developerworks/cn/java/j-lo-jsouphtml/index.html
***

Mainly used 3rd plugin:
- jsoup, who can help to parse HTML

***
#### Code snipet

```
div class="page-box house-lst-page-box">
  ...
</div>
```

To select above class, who has space. we use following code
```
Elements pageInfoEle = document.select("div.page-box.house-lst-page-box");
```
