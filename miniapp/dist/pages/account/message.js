'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _wepy = require('./../../npm/wepy/lib/wepy.js');

var _wepy2 = _interopRequireDefault(_wepy);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var AccountMessage = function (_wepy$page) {
  _inherits(AccountMessage, _wepy$page);

  function AccountMessage() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, AccountMessage);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = AccountMessage.__proto__ || Object.getPrototypeOf(AccountMessage)).call.apply(_ref, [this].concat(args))), _this), _this.config = {
      backgroundTextStyle: 'light',
      navigationBarBackgroundColor: '#fff',
      navigationBarTitleText: '留言',
      navigationBarTextStyle: 'black'
    }, _this.methods = {}, _this.data = {
      bindValue: ''
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  _createClass(AccountMessage, [{
    key: 'onLoad',
    value: function onLoad() {}
  }, {
    key: 'goBack',
    value: function goBack() {}
  }, {
    key: 'bindFormSubmit',
    value: function bindFormSubmit(e) {
      console.log(e.detail.value.textarea);
      var url = 'taurus/add/feedback';
      _wepy2.default.request({
        url: this.$parent.globalData.domain + url,
        method: 'POST',
        data: {
          token: this.$parent.globalData.token,
          message: e.detail.value.textarea
        }
      }).then(function (r) {
        console.dir(r);
        if (r.statusCode === 200) {
          wx.showToast({
            title: '提交成功',
            icon: 'success',
            duration: 2000,
            success: function success() {
              setTimeout(function () {
                wx.navigateBack();
              }, 2000);
            }
          });
        }
      });
    }
  }]);

  return AccountMessage;
}(_wepy2.default.page);


Page(require('./../../npm/wepy/lib/wepy.js').default.$createPage(AccountMessage , 'pages/account/message'));

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1lc3NhZ2UuanMiXSwibmFtZXMiOlsiQWNjb3VudE1lc3NhZ2UiLCJjb25maWciLCJiYWNrZ3JvdW5kVGV4dFN0eWxlIiwibmF2aWdhdGlvbkJhckJhY2tncm91bmRDb2xvciIsIm5hdmlnYXRpb25CYXJUaXRsZVRleHQiLCJuYXZpZ2F0aW9uQmFyVGV4dFN0eWxlIiwibWV0aG9kcyIsImRhdGEiLCJiaW5kVmFsdWUiLCJlIiwiY29uc29sZSIsImxvZyIsImRldGFpbCIsInZhbHVlIiwidGV4dGFyZWEiLCJ1cmwiLCJyZXF1ZXN0IiwiJHBhcmVudCIsImdsb2JhbERhdGEiLCJkb21haW4iLCJtZXRob2QiLCJ0b2tlbiIsIm1lc3NhZ2UiLCJ0aGVuIiwiciIsImRpciIsInN0YXR1c0NvZGUiLCJ3eCIsInNob3dUb2FzdCIsInRpdGxlIiwiaWNvbiIsImR1cmF0aW9uIiwic3VjY2VzcyIsInNldFRpbWVvdXQiLCJuYXZpZ2F0ZUJhY2siLCJwYWdlIl0sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7QUFDQTs7Ozs7Ozs7Ozs7O0lBQ3FCQSxjOzs7Ozs7Ozs7Ozs7OztzTUFDbkJDLE0sR0FBUztBQUNQQywyQkFBcUIsT0FEZDtBQUVQQyxvQ0FBOEIsTUFGdkI7QUFHUEMsOEJBQXdCLElBSGpCO0FBSVBDLDhCQUF3QjtBQUpqQixLLFFBTVRDLE8sR0FBVSxFLFFBR1ZDLEksR0FBTztBQUNMQyxpQkFBVztBQUROLEs7Ozs7OzZCQUdFLENBRVI7Ozs2QkFDUSxDQUVSOzs7bUNBQ2NDLEMsRUFBRztBQUNoQkMsY0FBUUMsR0FBUixDQUFZRixFQUFFRyxNQUFGLENBQVNDLEtBQVQsQ0FBZUMsUUFBM0I7QUFDQSxVQUFJQyxNQUFNLHFCQUFWO0FBQ0EscUJBQUtDLE9BQUwsQ0FBYTtBQUNYRCxhQUFLLEtBQUtFLE9BQUwsQ0FBYUMsVUFBYixDQUF3QkMsTUFBeEIsR0FBaUNKLEdBRDNCO0FBRVhLLGdCQUFRLE1BRkc7QUFHWGIsY0FBTTtBQUNKYyxpQkFBTyxLQUFLSixPQUFMLENBQWFDLFVBQWIsQ0FBd0JHLEtBRDNCO0FBRUpDLG1CQUFTYixFQUFFRyxNQUFGLENBQVNDLEtBQVQsQ0FBZUM7QUFGcEI7QUFISyxPQUFiLEVBT0dTLElBUEgsQ0FPUSxVQUFDQyxDQUFELEVBQU87QUFDYmQsZ0JBQVFlLEdBQVIsQ0FBWUQsQ0FBWjtBQUNBLFlBQUlBLEVBQUVFLFVBQUYsS0FBaUIsR0FBckIsRUFBMEI7QUFDeEJDLGFBQUdDLFNBQUgsQ0FBYTtBQUNYQyxtQkFBTyxNQURJO0FBRVhDLGtCQUFNLFNBRks7QUFHWEMsc0JBQVUsSUFIQztBQUlYQyxxQkFBUyxtQkFBWTtBQUNuQkMseUJBQVcsWUFBTTtBQUNmTixtQkFBR08sWUFBSDtBQUNELGVBRkQsRUFFRyxJQUZIO0FBR0Q7QUFSVSxXQUFiO0FBVUQ7QUFDRixPQXJCRDtBQXNCRDs7OztFQTVDeUMsZUFBS0MsSTs7a0JBQTVCbkMsYyIsImZpbGUiOiJtZXNzYWdlLmpzIiwic291cmNlc0NvbnRlbnQiOlsiXG5pbXBvcnQgd2VweSBmcm9tICd3ZXB5J1xuZXhwb3J0IGRlZmF1bHQgY2xhc3MgQWNjb3VudE1lc3NhZ2UgZXh0ZW5kcyB3ZXB5LnBhZ2Uge1xuICBjb25maWcgPSB7XG4gICAgYmFja2dyb3VuZFRleHRTdHlsZTogJ2xpZ2h0JyxcbiAgICBuYXZpZ2F0aW9uQmFyQmFja2dyb3VuZENvbG9yOiAnI2ZmZicsXG4gICAgbmF2aWdhdGlvbkJhclRpdGxlVGV4dDogJ+eVmeiogCcsXG4gICAgbmF2aWdhdGlvbkJhclRleHRTdHlsZTogJ2JsYWNrJ1xuICB9XG4gIG1ldGhvZHMgPSB7XG4gICAgXG4gIH1cbiAgZGF0YSA9IHtcbiAgICBiaW5kVmFsdWU6ICcnXG4gIH1cbiAgb25Mb2FkKCkge1xuXG4gIH1cbiAgZ29CYWNrKCkge1xuXG4gIH1cbiAgYmluZEZvcm1TdWJtaXQoZSkge1xuICAgIGNvbnNvbGUubG9nKGUuZGV0YWlsLnZhbHVlLnRleHRhcmVhKVxuICAgIGxldCB1cmwgPSAndGF1cnVzL2FkZC9mZWVkYmFjaydcbiAgICB3ZXB5LnJlcXVlc3Qoe1xuICAgICAgdXJsOiB0aGlzLiRwYXJlbnQuZ2xvYmFsRGF0YS5kb21haW4gKyB1cmwsXG4gICAgICBtZXRob2Q6ICdQT1NUJyxcbiAgICAgIGRhdGE6IHtcbiAgICAgICAgdG9rZW46IHRoaXMuJHBhcmVudC5nbG9iYWxEYXRhLnRva2VuLFxuICAgICAgICBtZXNzYWdlOiBlLmRldGFpbC52YWx1ZS50ZXh0YXJlYVxuICAgICAgfVxuICAgIH0pLnRoZW4oKHIpID0+IHtcbiAgICAgIGNvbnNvbGUuZGlyKHIpXG4gICAgICBpZiAoci5zdGF0dXNDb2RlID09PSAyMDApIHtcbiAgICAgICAgd3guc2hvd1RvYXN0KHtcbiAgICAgICAgICB0aXRsZTogJ+aPkOS6pOaIkOWKnycsXG4gICAgICAgICAgaWNvbjogJ3N1Y2Nlc3MnLFxuICAgICAgICAgIGR1cmF0aW9uOiAyMDAwLFxuICAgICAgICAgIHN1Y2Nlc3M6IGZ1bmN0aW9uICgpIHtcbiAgICAgICAgICAgIHNldFRpbWVvdXQoKCkgPT4ge1xuICAgICAgICAgICAgICB3eC5uYXZpZ2F0ZUJhY2soKVxuICAgICAgICAgICAgfSwgMjAwMCk7XG4gICAgICAgICAgfVxuICAgICAgICB9KVxuICAgICAgfVxuICAgIH0pXG4gIH1cbn1cbiJdfQ==