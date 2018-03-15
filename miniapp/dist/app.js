'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _wepy = require('./npm/wepy/lib/wepy.js');

var _wepy2 = _interopRequireDefault(_wepy);

require('./npm/wepy-async-function/index.js');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var _default = function (_wepy$app) {
  _inherits(_default, _wepy$app);

  function _default() {
    _classCallCheck(this, _default);

    var _this = _possibleConstructorReturn(this, (_default.__proto__ || Object.getPrototypeOf(_default)).call(this));

    _this.config = {
      pages: ['pages/home/index', 'pages/home/lessons/index', 'pages/home/lessons/fill', 'pages/home/lessons/match', 'pages/discovery/index', 'pages/discovery/buy', 'pages/account/index', 'pages/account/reward', 'pages/account/medal', 'pages/account/setting', 'pages/account/message'],
      window: {
        backgroundTextStyle: 'light',
        navigationBarBackgroundColor: '#fff',
        navigationBarTitleText: '雁字',
        navigationBarTextStyle: 'black'
      },
      tabBar: {
        color: '#888',
        selectedColor: '#506fe6',
        backgroundColor: '#fff',
        borderStyle: 'black',
        list: [{
          pagePath: 'pages/home/index',
          text: '戏读',
          iconPath: 'assets/img/global/home.png',
          selectedIconPath: 'assets/img/global/home-selected.png'
        }, {
          pagePath: 'pages/discovery/index',
          text: '发现',
          iconPath: 'assets/img/global/discovery.png',
          selectedIconPath: 'assets/img/global/discovery-selected.png'
        }, {
          pagePath: 'pages/account/index',
          text: '我的',
          iconPath: 'assets/img/global/account.png',
          selectedIconPath: 'assets/img/global/account-selected.png'
        }]
      }
    };
    _this.globalData = {
      userInfo: {},
      windowH: 0,
      token: '242b710b-c7fd-4e10-b30a-aca32b073985',
      domain: 'http://47.96.6.111:8080/'
    };

    _this.use('promisify');
    return _this;
  }

  _createClass(_default, [{
    key: 'onLaunch',
    value: function onLaunch() {
      this.init();
      _wepy2.default.login().then(function (r) {
        console.dir(r);
      });
    }
  }, {
    key: 'init',
    value: function init() {
      var _this2 = this;

      _wepy2.default.getUserInfo().then(function (r) {
        _this2.globalData.userInfo = r.userInfo;
      });
      if (this.globalData.windowH) {
        return this.globalData.windowH;
      }
      _wepy2.default.getSystemInfo().then(function (d) {
        _this2.globalData.windowH = d.windowHeight;
      });
    }
  }]);

  return _default;
}(_wepy2.default.app);


App(require('./npm/wepy/lib/wepy.js').default.$createApp(_default, {"noPromiseAPI":["createSelectorQuery"]}));
require('./_wepylogs.js')

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFwcC5qcyJdLCJuYW1lcyI6WyJjb25maWciLCJwYWdlcyIsIndpbmRvdyIsImJhY2tncm91bmRUZXh0U3R5bGUiLCJuYXZpZ2F0aW9uQmFyQmFja2dyb3VuZENvbG9yIiwibmF2aWdhdGlvbkJhclRpdGxlVGV4dCIsIm5hdmlnYXRpb25CYXJUZXh0U3R5bGUiLCJ0YWJCYXIiLCJjb2xvciIsInNlbGVjdGVkQ29sb3IiLCJiYWNrZ3JvdW5kQ29sb3IiLCJib3JkZXJTdHlsZSIsImxpc3QiLCJwYWdlUGF0aCIsInRleHQiLCJpY29uUGF0aCIsInNlbGVjdGVkSWNvblBhdGgiLCJnbG9iYWxEYXRhIiwidXNlckluZm8iLCJ3aW5kb3dIIiwidG9rZW4iLCJkb21haW4iLCJ1c2UiLCJpbml0IiwibG9naW4iLCJ0aGVuIiwiciIsImNvbnNvbGUiLCJkaXIiLCJnZXRVc2VySW5mbyIsImdldFN5c3RlbUluZm8iLCJkIiwid2luZG93SGVpZ2h0IiwiYXBwIl0sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7QUFDQTs7OztBQUNBOzs7Ozs7Ozs7Ozs7O0FBeURFLHNCQUFlO0FBQUE7O0FBQUE7O0FBQUEsVUF2RGZBLE1BdURlLEdBdkROO0FBQ1BDLGFBQU8sQ0FDTCxrQkFESyxFQUVMLDBCQUZLLEVBR0wseUJBSEssRUFJTCwwQkFKSyxFQUtMLHVCQUxLLEVBTUwscUJBTkssRUFPTCxxQkFQSyxFQVFMLHNCQVJLLEVBU0wscUJBVEssRUFVTCx1QkFWSyxFQVdMLHVCQVhLLENBREE7QUFjUEMsY0FBUTtBQUNOQyw2QkFBcUIsT0FEZjtBQUVOQyxzQ0FBOEIsTUFGeEI7QUFHTkMsZ0NBQXdCLElBSGxCO0FBSU5DLGdDQUF3QjtBQUpsQixPQWREO0FBb0JQQyxjQUFRO0FBQ05DLGVBQU8sTUFERDtBQUVOQyx1QkFBZSxTQUZUO0FBR05DLHlCQUFpQixNQUhYO0FBSU5DLHFCQUFhLE9BSlA7QUFLTkMsY0FBTSxDQUNKO0FBQ0VDLG9CQUFVLGtCQURaO0FBRUVDLGdCQUFNLElBRlI7QUFHRUMsb0JBQVUsNEJBSFo7QUFJRUMsNEJBQWtCO0FBSnBCLFNBREksRUFPSjtBQUNFSCxvQkFBVSx1QkFEWjtBQUVFQyxnQkFBTSxJQUZSO0FBR0VDLG9CQUFVLGlDQUhaO0FBSUVDLDRCQUFrQjtBQUpwQixTQVBJLEVBYUo7QUFDRUgsb0JBQVUscUJBRFo7QUFFRUMsZ0JBQU0sSUFGUjtBQUdFQyxvQkFBVSwrQkFIWjtBQUlFQyw0QkFBa0I7QUFKcEIsU0FiSTtBQUxBO0FBcEJELEtBdURNO0FBQUEsVUFQZkMsVUFPZSxHQVBGO0FBQ1hDLGdCQUFVLEVBREM7QUFFWEMsZUFBUyxDQUZFO0FBR1hDLGFBQU8sc0NBSEk7QUFJWEMsY0FBUTtBQUpHLEtBT0U7O0FBRWIsVUFBS0MsR0FBTCxDQUFTLFdBQVQ7QUFGYTtBQUdkOzs7OytCQUVVO0FBQ1QsV0FBS0MsSUFBTDtBQUNBLHFCQUFLQyxLQUFMLEdBQWFDLElBQWIsQ0FBa0IsVUFBQ0MsQ0FBRCxFQUFPO0FBQ3ZCQyxnQkFBUUMsR0FBUixDQUFZRixDQUFaO0FBQ0QsT0FGRDtBQUdEOzs7MkJBRU07QUFBQTs7QUFDTCxxQkFBS0csV0FBTCxHQUFtQkosSUFBbkIsQ0FBeUIsVUFBQ0MsQ0FBRCxFQUFPO0FBQzlCLGVBQUtULFVBQUwsQ0FBZ0JDLFFBQWhCLEdBQTJCUSxFQUFFUixRQUE3QjtBQUNELE9BRkQ7QUFHQSxVQUFJLEtBQUtELFVBQUwsQ0FBZ0JFLE9BQXBCLEVBQTZCO0FBQzNCLGVBQU8sS0FBS0YsVUFBTCxDQUFnQkUsT0FBdkI7QUFDRDtBQUNELHFCQUFLVyxhQUFMLEdBQXFCTCxJQUFyQixDQUEyQixVQUFDTSxDQUFELEVBQU87QUFDaEMsZUFBS2QsVUFBTCxDQUFnQkUsT0FBaEIsR0FBMEJZLEVBQUVDLFlBQTVCO0FBQ0QsT0FGRDtBQUdEOzs7O0VBOUUwQixlQUFLQyxHIiwiZmlsZSI6ImFwcC5qcyIsInNvdXJjZXNDb250ZW50IjpbIlxuaW1wb3J0IHdlcHkgZnJvbSAnd2VweSdcbmltcG9ydCAnd2VweS1hc3luYy1mdW5jdGlvbidcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIGV4dGVuZHMgd2VweS5hcHAge1xuICBjb25maWcgPSB7XG4gICAgcGFnZXM6IFtcbiAgICAgICdwYWdlcy9ob21lL2luZGV4JyxcbiAgICAgICdwYWdlcy9ob21lL2xlc3NvbnMvaW5kZXgnLFxuICAgICAgJ3BhZ2VzL2hvbWUvbGVzc29ucy9maWxsJyxcbiAgICAgICdwYWdlcy9ob21lL2xlc3NvbnMvbWF0Y2gnLFxuICAgICAgJ3BhZ2VzL2Rpc2NvdmVyeS9pbmRleCcsXG4gICAgICAncGFnZXMvZGlzY292ZXJ5L2J1eScsXG4gICAgICAncGFnZXMvYWNjb3VudC9pbmRleCcsXG4gICAgICAncGFnZXMvYWNjb3VudC9yZXdhcmQnLFxuICAgICAgJ3BhZ2VzL2FjY291bnQvbWVkYWwnLFxuICAgICAgJ3BhZ2VzL2FjY291bnQvc2V0dGluZycsXG4gICAgICAncGFnZXMvYWNjb3VudC9tZXNzYWdlJ1xuICAgIF0sXG4gICAgd2luZG93OiB7XG4gICAgICBiYWNrZ3JvdW5kVGV4dFN0eWxlOiAnbGlnaHQnLFxuICAgICAgbmF2aWdhdGlvbkJhckJhY2tncm91bmRDb2xvcjogJyNmZmYnLFxuICAgICAgbmF2aWdhdGlvbkJhclRpdGxlVGV4dDogJ+mbgeWtlycsXG4gICAgICBuYXZpZ2F0aW9uQmFyVGV4dFN0eWxlOiAnYmxhY2snXG4gICAgfSxcbiAgICB0YWJCYXI6IHtcbiAgICAgIGNvbG9yOiAnIzg4OCcsXG4gICAgICBzZWxlY3RlZENvbG9yOiAnIzUwNmZlNicsXG4gICAgICBiYWNrZ3JvdW5kQ29sb3I6ICcjZmZmJyxcbiAgICAgIGJvcmRlclN0eWxlOiAnYmxhY2snLFxuICAgICAgbGlzdDogW1xuICAgICAgICB7XG4gICAgICAgICAgcGFnZVBhdGg6ICdwYWdlcy9ob21lL2luZGV4JyxcbiAgICAgICAgICB0ZXh0OiAn5oiP6K+7JyxcbiAgICAgICAgICBpY29uUGF0aDogJ2Fzc2V0cy9pbWcvZ2xvYmFsL2hvbWUucG5nJyxcbiAgICAgICAgICBzZWxlY3RlZEljb25QYXRoOiAnYXNzZXRzL2ltZy9nbG9iYWwvaG9tZS1zZWxlY3RlZC5wbmcnXG4gICAgICAgIH0sXG4gICAgICAgIHtcbiAgICAgICAgICBwYWdlUGF0aDogJ3BhZ2VzL2Rpc2NvdmVyeS9pbmRleCcsXG4gICAgICAgICAgdGV4dDogJ+WPkeeOsCcsXG4gICAgICAgICAgaWNvblBhdGg6ICdhc3NldHMvaW1nL2dsb2JhbC9kaXNjb3ZlcnkucG5nJyxcbiAgICAgICAgICBzZWxlY3RlZEljb25QYXRoOiAnYXNzZXRzL2ltZy9nbG9iYWwvZGlzY292ZXJ5LXNlbGVjdGVkLnBuZydcbiAgICAgICAgfSxcbiAgICAgICAge1xuICAgICAgICAgIHBhZ2VQYXRoOiAncGFnZXMvYWNjb3VudC9pbmRleCcsXG4gICAgICAgICAgdGV4dDogJ+aIkeeahCcsXG4gICAgICAgICAgaWNvblBhdGg6ICdhc3NldHMvaW1nL2dsb2JhbC9hY2NvdW50LnBuZycsXG4gICAgICAgICAgc2VsZWN0ZWRJY29uUGF0aDogJ2Fzc2V0cy9pbWcvZ2xvYmFsL2FjY291bnQtc2VsZWN0ZWQucG5nJ1xuICAgICAgICB9XG4gICAgICBdXG4gICAgfVxuICB9XG5cbiAgZ2xvYmFsRGF0YSA9IHtcbiAgICB1c2VySW5mbzoge30sXG4gICAgd2luZG93SDogMCxcbiAgICB0b2tlbjogJzI0MmI3MTBiLWM3ZmQtNGUxMC1iMzBhLWFjYTMyYjA3Mzk4NScsXG4gICAgZG9tYWluOiAnaHR0cDovLzQ3Ljk2LjYuMTExOjgwODAvJ1xuICB9XG5cbiAgY29uc3RydWN0b3IgKCkge1xuICAgIHN1cGVyKClcbiAgICB0aGlzLnVzZSgncHJvbWlzaWZ5Jyk7XG4gIH1cblxuICBvbkxhdW5jaCgpIHtcbiAgICB0aGlzLmluaXQoKVxuICAgIHdlcHkubG9naW4oKS50aGVuKChyKSA9PiB7XG4gICAgICBjb25zb2xlLmRpcihyKVxuICAgIH0pXG4gIH1cblxuICBpbml0KCkge1xuICAgIHdlcHkuZ2V0VXNlckluZm8oKS50aGVuKCAocikgPT4ge1xuICAgICAgdGhpcy5nbG9iYWxEYXRhLnVzZXJJbmZvID0gci51c2VySW5mb1xuICAgIH0pXG4gICAgaWYgKHRoaXMuZ2xvYmFsRGF0YS53aW5kb3dIKSB7XG4gICAgICByZXR1cm4gdGhpcy5nbG9iYWxEYXRhLndpbmRvd0hcbiAgICB9XG4gICAgd2VweS5nZXRTeXN0ZW1JbmZvKCkudGhlbiggKGQpID0+IHtcbiAgICAgIHRoaXMuZ2xvYmFsRGF0YS53aW5kb3dIID0gZC53aW5kb3dIZWlnaHRcbiAgICB9KVxuICB9XG59XG4iXX0=