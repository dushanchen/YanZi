'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _wepy = require('./../../../npm/wepy/lib/wepy.js');

var _wepy2 = _interopRequireDefault(_wepy);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var FillPart = function (_wepy$page) {
  _inherits(FillPart, _wepy$page);

  function FillPart() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, FillPart);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = FillPart.__proto__ || Object.getPrototypeOf(FillPart)).call.apply(_ref, [this].concat(args))), _this), _this.data = {
      windowH: 0,
      options: {},
      q: {},
      a: {
        id: null,
        value: ''
      },
      longtap: false,
      remind: {}
    }, _this.config = {
      "backgroundColor": "#ebebeb"
    }, _this.methods = {
      handleAnswer: function handleAnswer(index) {
        console.log(index);
        this.a = this.q.answers[index];
      },
      handleLongPress: function handleLongPress(e) {
        this.longtap = true;
        this.remind = e;
        console.dir(this.remind);
      }
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  _createClass(FillPart, [{
    key: 'onLoad',
    value: function onLoad(options) {
      this.windowH = this.$parent.globalData.windowH;
      this.getLessonInfo();
    }
  }, {
    key: 'getLessonInfo',
    value: function getLessonInfo() {
      var _this2 = this;

      _wepy2.default.request({
        url: this.$parent.globalData.domain + 'pisces/load/question?courseId=1&index=1&lessonId=1'
      }).then(function (r) {
        _this2.q = r.data.message.question;
        _this2.$apply();
      });
    }
  }]);

  return FillPart;
}(_wepy2.default.page);


Page(require('./../../../npm/wepy/lib/wepy.js').default.$createPage(FillPart , 'pages/home/lessons/fill'));

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImZpbGwuanMiXSwibmFtZXMiOlsiRmlsbFBhcnQiLCJkYXRhIiwid2luZG93SCIsIm9wdGlvbnMiLCJxIiwiYSIsImlkIiwidmFsdWUiLCJsb25ndGFwIiwicmVtaW5kIiwiY29uZmlnIiwibWV0aG9kcyIsImhhbmRsZUFuc3dlciIsImluZGV4IiwiY29uc29sZSIsImxvZyIsImFuc3dlcnMiLCJoYW5kbGVMb25nUHJlc3MiLCJlIiwiZGlyIiwiJHBhcmVudCIsImdsb2JhbERhdGEiLCJnZXRMZXNzb25JbmZvIiwicmVxdWVzdCIsInVybCIsImRvbWFpbiIsInRoZW4iLCJyIiwibWVzc2FnZSIsInF1ZXN0aW9uIiwiJGFwcGx5IiwicGFnZSJdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7O0FBQ0E7Ozs7Ozs7Ozs7OztJQUNxQkEsUTs7Ozs7Ozs7Ozs7Ozs7MExBQ25CQyxJLEdBQU87QUFDTEMsZUFBUyxDQURKO0FBRUxDLGVBQVMsRUFGSjtBQUdMQyxTQUFHLEVBSEU7QUFJTEMsU0FBRztBQUNEQyxZQUFJLElBREg7QUFFREMsZUFBTztBQUZOLE9BSkU7QUFRTEMsZUFBUyxLQVJKO0FBU0xDLGNBQVE7QUFUSCxLLFFBV1BDLE0sR0FBUztBQUNQLHlCQUFtQjtBQURaLEssUUFPVEMsTyxHQUFVO0FBQ1JDLGtCQURRLHdCQUNLQyxLQURMLEVBQ1k7QUFDbEJDLGdCQUFRQyxHQUFSLENBQVlGLEtBQVo7QUFDQSxhQUFLUixDQUFMLEdBQVMsS0FBS0QsQ0FBTCxDQUFPWSxPQUFQLENBQWVILEtBQWYsQ0FBVDtBQUNELE9BSk87QUFLUkkscUJBTFEsMkJBS1FDLENBTFIsRUFLVztBQUNqQixhQUFLVixPQUFMLEdBQWUsSUFBZjtBQUNBLGFBQUtDLE1BQUwsR0FBY1MsQ0FBZDtBQUNBSixnQkFBUUssR0FBUixDQUFZLEtBQUtWLE1BQWpCO0FBQ0Q7QUFUTyxLOzs7OzsyQkFKSE4sTyxFQUFTO0FBQ2QsV0FBS0QsT0FBTCxHQUFlLEtBQUtrQixPQUFMLENBQWFDLFVBQWIsQ0FBd0JuQixPQUF2QztBQUNBLFdBQUtvQixhQUFMO0FBQ0Q7OztvQ0FZZTtBQUFBOztBQUNkLHFCQUFLQyxPQUFMLENBQWE7QUFDWEMsYUFBSyxLQUFLSixPQUFMLENBQWFDLFVBQWIsQ0FBd0JJLE1BQXhCLEdBQWlDO0FBRDNCLE9BQWIsRUFFR0MsSUFGSCxDQUVRLFVBQUNDLENBQUQsRUFBTztBQUNiLGVBQUt2QixDQUFMLEdBQVN1QixFQUFFMUIsSUFBRixDQUFPMkIsT0FBUCxDQUFlQyxRQUF4QjtBQUNBLGVBQUtDLE1BQUw7QUFDRCxPQUxEO0FBTUQ7Ozs7RUFyQ21DLGVBQUtDLEk7O2tCQUF0Qi9CLFEiLCJmaWxlIjoiZmlsbC5qcyIsInNvdXJjZXNDb250ZW50IjpbIlxuaW1wb3J0IHdlcHkgZnJvbSAnd2VweSdcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIEZpbGxQYXJ0IGV4dGVuZHMgd2VweS5wYWdlIHtcbiAgZGF0YSA9IHtcbiAgICB3aW5kb3dIOiAwLFxuICAgIG9wdGlvbnM6IHt9LFxuICAgIHE6IHt9LFxuICAgIGE6IHtcbiAgICAgIGlkOiBudWxsLFxuICAgICAgdmFsdWU6ICcnXG4gICAgfSxcbiAgICBsb25ndGFwOiBmYWxzZSxcbiAgICByZW1pbmQ6IHt9XG4gIH1cbiAgY29uZmlnID0ge1xuICAgIFwiYmFja2dyb3VuZENvbG9yXCI6IFwiI2ViZWJlYlwiXG4gIH1cbiAgb25Mb2FkKG9wdGlvbnMpIHtcbiAgICB0aGlzLndpbmRvd0ggPSB0aGlzLiRwYXJlbnQuZ2xvYmFsRGF0YS53aW5kb3dIXG4gICAgdGhpcy5nZXRMZXNzb25JbmZvKClcbiAgfVxuICBtZXRob2RzID0ge1xuICAgIGhhbmRsZUFuc3dlcihpbmRleCkge1xuICAgICAgY29uc29sZS5sb2coaW5kZXgpXG4gICAgICB0aGlzLmEgPSB0aGlzLnEuYW5zd2Vyc1tpbmRleF1cbiAgICB9LFxuICAgIGhhbmRsZUxvbmdQcmVzcyhlKSB7XG4gICAgICB0aGlzLmxvbmd0YXAgPSB0cnVlXG4gICAgICB0aGlzLnJlbWluZCA9IGVcbiAgICAgIGNvbnNvbGUuZGlyKHRoaXMucmVtaW5kKVxuICAgIH1cbiAgfVxuICBnZXRMZXNzb25JbmZvKCkge1xuICAgIHdlcHkucmVxdWVzdCh7XG4gICAgICB1cmw6IHRoaXMuJHBhcmVudC5nbG9iYWxEYXRhLmRvbWFpbiArICdwaXNjZXMvbG9hZC9xdWVzdGlvbj9jb3Vyc2VJZD0xJmluZGV4PTEmbGVzc29uSWQ9MSdcbiAgICB9KS50aGVuKChyKSA9PiB7XG4gICAgICB0aGlzLnEgPSByLmRhdGEubWVzc2FnZS5xdWVzdGlvblxuICAgICAgdGhpcy4kYXBwbHkoKVxuICAgIH0pXG4gIH1cbn1cbiJdfQ==