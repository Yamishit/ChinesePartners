/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	var __weex_template__ = __webpack_require__(1)
	var __weex_style__ = __webpack_require__(2)
	var __weex_script__ = __webpack_require__(3)

	__weex_define__('@weex-component/b7025e963afa7fcf7adaba87fe731239', [], function(__weex_require__, __weex_exports__, __weex_module__) {

	    __weex_script__(__weex_module__, __weex_exports__, __weex_require__)
	    if (__weex_exports__.__esModule && __weex_exports__.default) {
	      __weex_module__.exports = __weex_exports__.default
	    }

	    __weex_module__.exports.template = __weex_template__

	    __weex_module__.exports.style = __weex_style__

	})

	__weex_bootstrap__('@weex-component/b7025e963afa7fcf7adaba87fe731239',undefined,undefined)

/***/ },
/* 1 */
/***/ function(module, exports) {

	module.exports = {
	  "type": "div",
	  "style": {
	    "flexDirection": "column"
	  },
	  "children": [
	    {
	      "type": "slider",
	      "classList": [
	        "slider"
	      ],
	      "attr": {
	        "interval": function () {return this.intervalValue},
	        "autoPlay": function () {return this.isAutoPlay}
	      },
	      "children": [
	        {
	          "type": "div",
	          "classList": [
	            "slider-pages"
	          ],
	          "repeat": function () {return this.itemList},
	          "events": {
	            "click": "goWeexSite"
	          },
	          "children": [
	            {
	              "type": "image",
	              "classList": [
	                "thumb"
	              ],
	              "attr": {
	                "src": function () {return this.pictureUrl}
	              }
	            },
	            {
	              "type": "text",
	              "classList": [
	                "title"
	              ],
	              "attr": {
	                "value": function () {return this.title}
	              }
	            }
	          ]
	        }
	      ]
	    },
	    {
	      "type": "div",
	      "classList": [
	        "container"
	      ],
	      "events": {
	        "click": "goWeexSite"
	      },
	      "children": [
	        {
	          "type": "div",
	          "classList": [
	            "cell"
	          ],
	          "children": [
	            {
	              "type": "image",
	              "classList": [
	                "thumb"
	              ],
	              "attr": {
	                "src": "http://t.cn/RGE3AJt"
	              }
	            },
	            {
	              "type": "text",
	              "classList": [
	                "title"
	              ],
	              "attr": {
	                "value": "Sk"
	              }
	            }
	          ]
	        },
	        {
	          "type": "div",
	          "classList": [
	            "cell"
	          ],
	          "children": [
	            {
	              "type": "image",
	              "classList": [
	                "thumb"
	              ],
	              "attr": {
	                "src": "http://t.cn/RGE3uo9"
	              }
	            },
	            {
	              "type": "text",
	              "classList": [
	                "title"
	              ],
	              "attr": {
	                "value": "Java"
	              }
	            }
	          ]
	        },
	        {
	          "type": "div",
	          "classList": [
	            "cell"
	          ],
	          "children": [
	            {
	              "type": "image",
	              "classList": [
	                "thumb"
	              ],
	              "attr": {
	                "src": "http://t.cn/RGE31hq"
	              }
	            },
	            {
	              "type": "text",
	              "classList": [
	                "title"
	              ],
	              "attr": {
	                "value": "jjjj C"
	              }
	            }
	          ]
	        }
	      ]
	    }
	  ]
	}

/***/ },
/* 2 */
/***/ function(module, exports) {

	module.exports = {
	  "cell": {
	    "marginTop": 10,
	    "marginLeft": 10,
	    "flexDirection": "row"
	  },
	  "thumb": {
	    "width": 200,
	    "height": 200
	  },
	  "title": {
	    "textAlign": "center",
	    "flex": 1,
	    "color": "#808080",
	    "fontSize": 50
	  },
	  "slider": {
	    "margin": 18,
	    "width": 714,
	    "height": 230
	  },
	  "slider-pages": {
	    "flexDirection": "row",
	    "width": 714,
	    "height": 200
	  }
	}

/***/ },
/* 3 */
/***/ function(module, exports) {

	module.exports = function(module, exports, __weex_require__){"use strict";

	module.exports = {
	  data: function () {return {
	    intervalValue: "1000",
	    isShowIndicators: "true",
	    isAutoPlay: "true",
	    itemList: [{ title: 'Java', pictureUrl: 'http://t.cn/RGE3uo9' }, { title: 'Objective C', pictureUrl: 'http://t.cn/RGE31hq' }, { title: 'JavaScript', pictureUrl: 'http://t.cn/RGE3AJt' }]
	  }},
	  methods: {
	    goWeexSite: function goWeexSite() {
	      this.$openURL('http://alibaba.github.io/weex/');
	    }
	  }
	};}
	/* generated by weex-loader */


/***/ }
/******/ ]);