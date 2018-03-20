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

var MatchPart = function (_wepy$page) {
  _inherits(MatchPart, _wepy$page);

  function MatchPart() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, MatchPart);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = MatchPart.__proto__ || Object.getPrototypeOf(MatchPart)).call.apply(_ref, [this].concat(args))), _this), _this.data = {
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

  _createClass(MatchPart, [{
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
        url: this.$parent.globalData.domain + 'pisces/load/question?courseId=1&index=3&lessonId=1'
      }).then(function (r) {
        _this2.q = r.data.message.question;
        _this2.$apply();
      });
    }
  }]);

  return MatchPart;
}(_wepy2.default.page);


Page(require('./../../../npm/wepy/lib/wepy.js').default.$createPage(MatchPart , 'pages/home/lessons/match'));

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1hdGNoLmpzIl0sIm5hbWVzIjpbIk1hdGNoUGFydCIsImRhdGEiLCJ3aW5kb3dIIiwib3B0aW9ucyIsInEiLCJhIiwiaWQiLCJ2YWx1ZSIsImxvbmd0YXAiLCJyZW1pbmQiLCJjb25maWciLCJtZXRob2RzIiwiaGFuZGxlQW5zd2VyIiwiaW5kZXgiLCJjb25zb2xlIiwibG9nIiwiYW5zd2VycyIsImhhbmRsZUxvbmdQcmVzcyIsImUiLCJkaXIiLCIkcGFyZW50IiwiZ2xvYmFsRGF0YSIsImdldExlc3NvbkluZm8iLCJyZXF1ZXN0IiwidXJsIiwiZG9tYWluIiwidGhlbiIsInIiLCJtZXNzYWdlIiwicXVlc3Rpb24iLCIkYXBwbHkiLCJwYWdlIl0sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7QUFDQTs7Ozs7Ozs7Ozs7O0lBQ3FCQSxTOzs7Ozs7Ozs7Ozs7Ozs0TEFDbkJDLEksR0FBTztBQUNMQyxlQUFTLENBREo7QUFFTEMsZUFBUyxFQUZKO0FBR0xDLFNBQUcsRUFIRTtBQUlMQyxTQUFHO0FBQ0RDLFlBQUksSUFESDtBQUVEQyxlQUFPO0FBRk4sT0FKRTtBQVFMQyxlQUFTLEtBUko7QUFTTEMsY0FBUTtBQVRILEssUUFXUEMsTSxHQUFTO0FBQ1AseUJBQW1CO0FBRFosSyxRQU9UQyxPLEdBQVU7QUFDUkMsa0JBRFEsd0JBQ0tDLEtBREwsRUFDWTtBQUNsQkMsZ0JBQVFDLEdBQVIsQ0FBWUYsS0FBWjtBQUNBLGFBQUtSLENBQUwsR0FBUyxLQUFLRCxDQUFMLENBQU9ZLE9BQVAsQ0FBZUgsS0FBZixDQUFUO0FBQ0QsT0FKTztBQUtSSSxxQkFMUSwyQkFLUUMsQ0FMUixFQUtXO0FBQ2pCLGFBQUtWLE9BQUwsR0FBZSxJQUFmO0FBQ0EsYUFBS0MsTUFBTCxHQUFjUyxDQUFkO0FBQ0FKLGdCQUFRSyxHQUFSLENBQVksS0FBS1YsTUFBakI7QUFDRDtBQVRPLEs7Ozs7OzJCQUpITixPLEVBQVM7QUFDZCxXQUFLRCxPQUFMLEdBQWUsS0FBS2tCLE9BQUwsQ0FBYUMsVUFBYixDQUF3Qm5CLE9BQXZDO0FBQ0EsV0FBS29CLGFBQUw7QUFDRDs7O29DQVllO0FBQUE7O0FBQ2QscUJBQUtDLE9BQUwsQ0FBYTtBQUNYQyxhQUFLLEtBQUtKLE9BQUwsQ0FBYUMsVUFBYixDQUF3QkksTUFBeEIsR0FBaUM7QUFEM0IsT0FBYixFQUVHQyxJQUZILENBRVEsVUFBQ0MsQ0FBRCxFQUFPO0FBQ2IsZUFBS3ZCLENBQUwsR0FBU3VCLEVBQUUxQixJQUFGLENBQU8yQixPQUFQLENBQWVDLFFBQXhCO0FBQ0EsZUFBS0MsTUFBTDtBQUNELE9BTEQ7QUFNRDs7OztFQXJDb0MsZUFBS0MsSTs7a0JBQXZCL0IsUyIsImZpbGUiOiJtYXRjaC5qcyIsInNvdXJjZXNDb250ZW50IjpbIlxuaW1wb3J0IHdlcHkgZnJvbSAnd2VweSdcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIE1hdGNoUGFydCBleHRlbmRzIHdlcHkucGFnZSB7XG4gIGRhdGEgPSB7XG4gICAgd2luZG93SDogMCxcbiAgICBvcHRpb25zOiB7fSxcbiAgICBxOiB7fSxcbiAgICBhOiB7XG4gICAgICBpZDogbnVsbCxcbiAgICAgIHZhbHVlOiAnJ1xuICAgIH0sXG4gICAgbG9uZ3RhcDogZmFsc2UsXG4gICAgcmVtaW5kOiB7fVxuICB9XG4gIGNvbmZpZyA9IHtcbiAgICBcImJhY2tncm91bmRDb2xvclwiOiBcIiNlYmViZWJcIlxuICB9XG4gIG9uTG9hZChvcHRpb25zKSB7XG4gICAgdGhpcy53aW5kb3dIID0gdGhpcy4kcGFyZW50Lmdsb2JhbERhdGEud2luZG93SFxuICAgIHRoaXMuZ2V0TGVzc29uSW5mbygpXG4gIH1cbiAgbWV0aG9kcyA9IHtcbiAgICBoYW5kbGVBbnN3ZXIoaW5kZXgpIHtcbiAgICAgIGNvbnNvbGUubG9nKGluZGV4KVxuICAgICAgdGhpcy5hID0gdGhpcy5xLmFuc3dlcnNbaW5kZXhdXG4gICAgfSxcbiAgICBoYW5kbGVMb25nUHJlc3MoZSkge1xuICAgICAgdGhpcy5sb25ndGFwID0gdHJ1ZVxuICAgICAgdGhpcy5yZW1pbmQgPSBlXG4gICAgICBjb25zb2xlLmRpcih0aGlzLnJlbWluZClcbiAgICB9XG4gIH1cbiAgZ2V0TGVzc29uSW5mbygpIHtcbiAgICB3ZXB5LnJlcXVlc3Qoe1xuICAgICAgdXJsOiB0aGlzLiRwYXJlbnQuZ2xvYmFsRGF0YS5kb21haW4gKyAncGlzY2VzL2xvYWQvcXVlc3Rpb24/Y291cnNlSWQ9MSZpbmRleD0zJmxlc3NvbklkPTEnXG4gICAgfSkudGhlbigocikgPT4ge1xuICAgICAgdGhpcy5xID0gci5kYXRhLm1lc3NhZ2UucXVlc3Rpb25cbiAgICAgIHRoaXMuJGFwcGx5KClcbiAgICB9KVxuICB9XG59XG4iXX0=