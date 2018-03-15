'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _wepy = require('./../../npm/wepy/lib/wepy.js');

var _wepy2 = _interopRequireDefault(_wepy);

var _classItem = require('./../../components/discovery/classItem.js');

var _classItem2 = _interopRequireDefault(_classItem);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var DiscoveryIndex = function (_wepy$page) {
  _inherits(DiscoveryIndex, _wepy$page);

  function DiscoveryIndex() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, DiscoveryIndex);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = DiscoveryIndex.__proto__ || Object.getPrototypeOf(DiscoveryIndex)).call.apply(_ref, [this].concat(args))), _this), _this.$repeat = { "list": { "com": "ClassItem", "props": "item.sync" } }, _this.$props = { "ClassItem": { "xmlns:v-bind": { "value": "", "for": "list", "item": "item", "index": "index", "key": "index" }, "v-bind:item.sync": { "value": "item", "type": "item", "for": "list", "item": "item", "index": "index", "key": "index" } } }, _this.$events = {}, _this.components = {
      ClassItem: _classItem2.default
    }, _this.data = {
      loading: true,
      windowH: 0,
      list: []
    }, _this.config = {
      backgroundTextStyle: 'light',
      navigationBarBackgroundColor: '#fff',
      navigationBarTitleText: '课程',
      navigationBarTextStyle: 'black'
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  _createClass(DiscoveryIndex, [{
    key: 'onLoad',
    value: function onLoad() {
      this.windowH = this.$parent.globalData.windowH;
      this.getData();
    }
  }, {
    key: 'scroll',
    value: function scroll(e) {
      // console.log(e)
    }
  }, {
    key: 'processStartTime',
    value: function processStartTime(d) {
      var date = new Date(d);
      var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '月';
      var D = date.getDate() + '日';
      return M + D + '开课';
    }
  }, {
    key: 'getData',
    value: function getData() {
      var _this2 = this;

      var url = 'http://47.96.6.111:8080/pisces/user/load/terms?token=';
      var token = this.$parent.globalData.token;
      _wepy2.default.request(url + token).then(function (d) {
        console.dir(d);
        _this2.list = d.data.message.termInfos;
        _this2.list.forEach(function (e) {
          e.termInfo.startTimeCn = _this2.processStartTime(e.termInfo.startTime);
        });
        _this2.loading = false;
        _this2.$apply();
      });
    }
  }]);

  return DiscoveryIndex;
}(_wepy2.default.page);


Page(require('./../../npm/wepy/lib/wepy.js').default.$createPage(DiscoveryIndex , 'pages/discovery/index'));

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImluZGV4LmpzIl0sIm5hbWVzIjpbIkRpc2NvdmVyeUluZGV4IiwiJHJlcGVhdCIsIiRwcm9wcyIsIiRldmVudHMiLCJjb21wb25lbnRzIiwiQ2xhc3NJdGVtIiwiZGF0YSIsImxvYWRpbmciLCJ3aW5kb3dIIiwibGlzdCIsImNvbmZpZyIsImJhY2tncm91bmRUZXh0U3R5bGUiLCJuYXZpZ2F0aW9uQmFyQmFja2dyb3VuZENvbG9yIiwibmF2aWdhdGlvbkJhclRpdGxlVGV4dCIsIm5hdmlnYXRpb25CYXJUZXh0U3R5bGUiLCIkcGFyZW50IiwiZ2xvYmFsRGF0YSIsImdldERhdGEiLCJlIiwiZCIsImRhdGUiLCJEYXRlIiwiTSIsImdldE1vbnRoIiwiRCIsImdldERhdGUiLCJ1cmwiLCJ0b2tlbiIsInJlcXVlc3QiLCJ0aGVuIiwiY29uc29sZSIsImRpciIsIm1lc3NhZ2UiLCJ0ZXJtSW5mb3MiLCJmb3JFYWNoIiwidGVybUluZm8iLCJzdGFydFRpbWVDbiIsInByb2Nlc3NTdGFydFRpbWUiLCJzdGFydFRpbWUiLCIkYXBwbHkiLCJwYWdlIl0sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7QUFDQTs7OztBQUNBOzs7Ozs7Ozs7Ozs7SUFFcUJBLGM7Ozs7Ozs7Ozs7Ozs7O3NNQUNwQkMsTyxHQUFVLEVBQUMsUUFBTyxFQUFDLE9BQU0sV0FBUCxFQUFtQixTQUFRLFdBQTNCLEVBQVIsRSxRQUNYQyxNLEdBQVMsRUFBQyxhQUFZLEVBQUMsZ0JBQWUsRUFBQyxTQUFRLEVBQVQsRUFBWSxPQUFNLE1BQWxCLEVBQXlCLFFBQU8sTUFBaEMsRUFBdUMsU0FBUSxPQUEvQyxFQUF1RCxPQUFNLE9BQTdELEVBQWhCLEVBQXNGLG9CQUFtQixFQUFDLFNBQVEsTUFBVCxFQUFnQixRQUFPLE1BQXZCLEVBQThCLE9BQU0sTUFBcEMsRUFBMkMsUUFBTyxNQUFsRCxFQUF5RCxTQUFRLE9BQWpFLEVBQXlFLE9BQU0sT0FBL0UsRUFBekcsRUFBYixFLFFBQ1RDLE8sR0FBVSxFLFFBQ1RDLFUsR0FBYTtBQUNWQztBQURVLEssUUFHWkMsSSxHQUFPO0FBQ0xDLGVBQVMsSUFESjtBQUVMQyxlQUFTLENBRko7QUFHTEMsWUFBTTtBQUhELEssUUFLUEMsTSxHQUFTO0FBQ1BDLDJCQUFxQixPQURkO0FBRVBDLG9DQUE4QixNQUZ2QjtBQUdQQyw4QkFBd0IsSUFIakI7QUFJUEMsOEJBQXdCO0FBSmpCLEs7Ozs7OzZCQU1BO0FBQ1AsV0FBS04sT0FBTCxHQUFlLEtBQUtPLE9BQUwsQ0FBYUMsVUFBYixDQUF3QlIsT0FBdkM7QUFDQSxXQUFLUyxPQUFMO0FBQ0Q7OzsyQkFDTUMsQyxFQUFHO0FBQ1I7QUFDRDs7O3FDQUNpQkMsQyxFQUFHO0FBQ25CLFVBQUlDLE9BQU8sSUFBSUMsSUFBSixDQUFTRixDQUFULENBQVg7QUFDQSxVQUFJRyxJQUFJLENBQUNGLEtBQUtHLFFBQUwsS0FBZ0IsQ0FBaEIsR0FBb0IsRUFBcEIsR0FBeUIsT0FBS0gsS0FBS0csUUFBTCxLQUFnQixDQUFyQixDQUF6QixHQUFtREgsS0FBS0csUUFBTCxLQUFnQixDQUFwRSxJQUF5RSxHQUFqRjtBQUNBLFVBQUlDLElBQUlKLEtBQUtLLE9BQUwsS0FBaUIsR0FBekI7QUFDQSxhQUFPSCxJQUFJRSxDQUFKLEdBQVEsSUFBZjtBQUNEOzs7OEJBQ1M7QUFBQTs7QUFDUixVQUFJRSxNQUFNLHVEQUFWO0FBQ0EsVUFBSUMsUUFBUSxLQUFLWixPQUFMLENBQWFDLFVBQWIsQ0FBd0JXLEtBQXBDO0FBQ0EscUJBQUtDLE9BQUwsQ0FBYUYsTUFBTUMsS0FBbkIsRUFBMEJFLElBQTFCLENBQ0UsVUFBQ1YsQ0FBRCxFQUFPO0FBQ0xXLGdCQUFRQyxHQUFSLENBQVlaLENBQVo7QUFDQSxlQUFLVixJQUFMLEdBQVlVLEVBQUViLElBQUYsQ0FBTzBCLE9BQVAsQ0FBZUMsU0FBM0I7QUFDQSxlQUFLeEIsSUFBTCxDQUFVeUIsT0FBVixDQUFrQixhQUFLO0FBQ3JCaEIsWUFBRWlCLFFBQUYsQ0FBV0MsV0FBWCxHQUF5QixPQUFLQyxnQkFBTCxDQUFzQm5CLEVBQUVpQixRQUFGLENBQVdHLFNBQWpDLENBQXpCO0FBQ0QsU0FGRDtBQUdBLGVBQUsvQixPQUFMLEdBQWUsS0FBZjtBQUNBLGVBQUtnQyxNQUFMO0FBQ0QsT0FUSDtBQVdEOzs7O0VBN0N5QyxlQUFLQyxJOztrQkFBNUJ4QyxjIiwiZmlsZSI6ImluZGV4LmpzIiwic291cmNlc0NvbnRlbnQiOlsiXG5pbXBvcnQgd2VweSBmcm9tICd3ZXB5J1xuaW1wb3J0IENsYXNzSXRlbSBmcm9tICcuLi8uLi9jb21wb25lbnRzL2Rpc2NvdmVyeS9jbGFzc0l0ZW0nXG5cbmV4cG9ydCBkZWZhdWx0IGNsYXNzIERpc2NvdmVyeUluZGV4IGV4dGVuZHMgd2VweS5wYWdlIHtcbiAkcmVwZWF0ID0ge1wibGlzdFwiOntcImNvbVwiOlwiQ2xhc3NJdGVtXCIsXCJwcm9wc1wiOlwiaXRlbS5zeW5jXCJ9fTtcclxuJHByb3BzID0ge1wiQ2xhc3NJdGVtXCI6e1wieG1sbnM6di1iaW5kXCI6e1widmFsdWVcIjpcIlwiLFwiZm9yXCI6XCJsaXN0XCIsXCJpdGVtXCI6XCJpdGVtXCIsXCJpbmRleFwiOlwiaW5kZXhcIixcImtleVwiOlwiaW5kZXhcIn0sXCJ2LWJpbmQ6aXRlbS5zeW5jXCI6e1widmFsdWVcIjpcIml0ZW1cIixcInR5cGVcIjpcIml0ZW1cIixcImZvclwiOlwibGlzdFwiLFwiaXRlbVwiOlwiaXRlbVwiLFwiaW5kZXhcIjpcImluZGV4XCIsXCJrZXlcIjpcImluZGV4XCJ9fX07XHJcbiRldmVudHMgPSB7fTtcclxuIGNvbXBvbmVudHMgPSB7XG4gICAgQ2xhc3NJdGVtOiBDbGFzc0l0ZW1cbiAgfVxuICBkYXRhID0ge1xuICAgIGxvYWRpbmc6IHRydWUsXG4gICAgd2luZG93SDogMCxcbiAgICBsaXN0OiBbXVxuICB9XG4gIGNvbmZpZyA9IHtcbiAgICBiYWNrZ3JvdW5kVGV4dFN0eWxlOiAnbGlnaHQnLFxuICAgIG5hdmlnYXRpb25CYXJCYWNrZ3JvdW5kQ29sb3I6ICcjZmZmJyxcbiAgICBuYXZpZ2F0aW9uQmFyVGl0bGVUZXh0OiAn6K++56iLJyxcbiAgICBuYXZpZ2F0aW9uQmFyVGV4dFN0eWxlOiAnYmxhY2snXG4gIH1cbiAgb25Mb2FkKCkge1xuICAgIHRoaXMud2luZG93SCA9IHRoaXMuJHBhcmVudC5nbG9iYWxEYXRhLndpbmRvd0hcbiAgICB0aGlzLmdldERhdGEoKVxuICB9XG4gIHNjcm9sbChlKSB7XG4gICAgLy8gY29uc29sZS5sb2coZSlcbiAgfVxuICBwcm9jZXNzU3RhcnRUaW1lIChkKSB7XG4gICAgbGV0IGRhdGUgPSBuZXcgRGF0ZShkKVxuICAgIGxldCBNID0gKGRhdGUuZ2V0TW9udGgoKSsxIDwgMTAgPyAnMCcrKGRhdGUuZ2V0TW9udGgoKSsxKSA6IGRhdGUuZ2V0TW9udGgoKSsxKSArICfmnIgnXG4gICAgbGV0IEQgPSBkYXRlLmdldERhdGUoKSArICfml6UnXG4gICAgcmV0dXJuIE0gKyBEICsgJ+W8gOivvidcbiAgfVxuICBnZXREYXRhKCkge1xuICAgIGxldCB1cmwgPSAnaHR0cDovLzQ3Ljk2LjYuMTExOjgwODAvcGlzY2VzL3VzZXIvbG9hZC90ZXJtcz90b2tlbj0nXG4gICAgbGV0IHRva2VuID0gdGhpcy4kcGFyZW50Lmdsb2JhbERhdGEudG9rZW5cbiAgICB3ZXB5LnJlcXVlc3QodXJsICsgdG9rZW4pLnRoZW4oXG4gICAgICAoZCkgPT4ge1xuICAgICAgICBjb25zb2xlLmRpcihkKVxuICAgICAgICB0aGlzLmxpc3QgPSBkLmRhdGEubWVzc2FnZS50ZXJtSW5mb3NcbiAgICAgICAgdGhpcy5saXN0LmZvckVhY2goZSA9PiB7XG4gICAgICAgICAgZS50ZXJtSW5mby5zdGFydFRpbWVDbiA9IHRoaXMucHJvY2Vzc1N0YXJ0VGltZShlLnRlcm1JbmZvLnN0YXJ0VGltZSlcbiAgICAgICAgfSk7XG4gICAgICAgIHRoaXMubG9hZGluZyA9IGZhbHNlXG4gICAgICAgIHRoaXMuJGFwcGx5KClcbiAgICAgIH1cbiAgICApO1xuICB9XG59XG4iXX0=