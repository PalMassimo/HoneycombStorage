(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6e9c552e"],{"41aa":function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("main",[n("h1",[e._v("Your Files")]),n("p",[e._v("These are the files you have uploaded")]),0!==e.uploadedFiles.length?n("table",[e._m(0),n("tbody",e._l(e.uploadedFiles,(function(t){return n("tr",{key:t.id},[n("td",[e._v(e._s(t.name))]),n("td",[e._v(e._s(t.size)+" byte")]),n("td",[e._v(e._s(t.uploadDate))]),n("td",{staticClass:"delete",on:{click:function(n){return e.deleteFile(t.id)}}},[n("img",{attrs:{src:"delete_button.png",alt:"bin"}})])])})),0)]):n("p",[e._v(" You haven't load any file yet. Go "),n("router-link",{staticClass:"router-link",attrs:{to:"/AddFile"}},[e._v("here")]),e._v(" to upload a file! ")],1)])},r=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("thead",[n("tr",[n("th",[e._v("Name")]),n("th",[e._v("Size")]),n("th",[e._v("Upload Date")]),n("th",[e._v("Delete")])])])}],i=(n("99af"),n("4de4"),n("bc3a")),o=n.n(i),l={name:"ManageFiles",methods:{deleteFile:function(e){var t=this,n=event.currentTarget.parentElement;o.a.delete("".concat("/api/","uploaderarea/file/").concat(e)).then((function(){t.uploadedFiles=t.uploadedFiles.filter((function(t){return t.id!==e})),n.remove()})).catch((function(){return alert("Non è stato possibile eliminare il file")}))}},data:function(){return{uploadedFiles:[]}},created:function(){var e=this;o.a.get("".concat("/api/","uploaderarea/files")).then((function(t){return e.uploadedFiles=t.data})).catch((function(e){return console.log(e)}))}},c=l,u=(n("ffe5"),n("2877")),d=Object(u["a"])(c,a,r,!1,null,"0ef9b4e0",null);t["default"]=d.exports},"4e42":function(e,t,n){},8418:function(e,t,n){"use strict";var a=n("c04e"),r=n("9bf2"),i=n("5c6c");e.exports=function(e,t,n){var o=a(t);o in e?r.f(e,o,i(0,n)):e[o]=n}},"99af":function(e,t,n){"use strict";var a=n("23e7"),r=n("d039"),i=n("e8b5"),o=n("861d"),l=n("7b0b"),c=n("50c4"),u=n("8418"),d=n("65f0"),s=n("1dde"),f=n("b622"),p=n("2d00"),h=f("isConcatSpreadable"),v=9007199254740991,_="Maximum allowed index exceeded",b=p>=51||!r((function(){var e=[];return e[h]=!1,e.concat()[0]!==e})),m=s("concat"),g=function(e){if(!o(e))return!1;var t=e[h];return void 0!==t?!!t:i(e)},F=!b||!m;a({target:"Array",proto:!0,forced:F},{concat:function(e){var t,n,a,r,i,o=l(this),s=d(o,0),f=0;for(t=-1,a=arguments.length;t<a;t++)if(i=-1===t?o:arguments[t],g(i)){if(r=c(i.length),f+r>v)throw TypeError(_);for(n=0;n<r;n++,f++)n in i&&u(s,f,i[n])}else{if(f>=v)throw TypeError(_);u(s,f++,i)}return s.length=f,s}})},ffe5:function(e,t,n){"use strict";var a=n("4e42"),r=n.n(a);r.a}}]);
//# sourceMappingURL=chunk-6e9c552e.b1a6e415.js.map