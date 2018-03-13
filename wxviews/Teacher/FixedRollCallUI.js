Page({
  data: { // 参与页面渲染的数据
    presentNum: 37,
    classInfo:"",
    time:0
  },
  onLoad: function (options) {
    var k = options.classId;
    this.setData({
      classInfo: wx.getStorageSync("classInfo" + k),
      //res代表success函数的事件对，data是固定的，stories是是上面json数据中stories
    })
    wx.setStorageSync("nextUrl", './FixedRollCallUI?classId=' + this.data.classInfo.id);
    wx.setStorageSync("id", this.data.classInfo.id);
  },



  FixedEndRollCallUI: function () {
    var that = this;
    wx.showModal({
      title: '注意',
      content: '确定要结束签到吗？',
      success: function (res) {
        if (res.confirm) {
          wx.redirectTo({
            url: './FixedEndRollCallUI?&classId='+that.data.classInfo.id,
            success: function () {
              var IPPort = getApp().globalData.IPPort;
              var message = "";
              wx.request({
                url: IPPort + '/class/' + that.data.classInfo.id,
                method: 'PUT',
                data: { "calling": -1 },
                success: function (data) {
                }
              });
            },
            fail: function () {
              // fail
            },
            complete: function () {
              // complete
            }
          })
        }
        else {
          console.log("asd");
        }
      }
    })
  },

  FixedGroupInfoUI: function () {
    wx.navigateTo({
      url: './FixedGroupInfoUI?classId='+this.data.classInfo.id,
      success: function (res) {
        // success
      },
      fail: function () {
        // fail
      },
      complete: function () {
        // complete
      }
    })
  },

  RollCallListUI: function () {
    wx.navigateTo({
      url: './RollCallListUI?classId=' + this.data.classInfo.id,
      success: function () {
        // success
      },
      fail: function () {
        // fail
      },
      complete: function () {
        // complete
      }
    })
  },


  onShow: function () {
    this.setData({
      time: 0
    })
    this.requestData(this)
  },

  onUnload: function () {
    //console.log('2222222222222222')
    this.setData({
      time: -2
    })
  },

  requestData: function (that) {
    if (that.data.time <= -1)
      return
    setTimeout(function () {
      that.setData({
        time: that.data.time + 1,
      })
      var IPPort = getApp().globalData.IPPort;
      var message = "";
      wx.request({
        url: IPPort + "/seminar/" + wx.getStorageSync("seminarId") + "/class/" + that.data.classInfo.id+"/attendance",
        method: 'GET',
        success: function (data) {
          that.setData({
            presentNum: data.data.numPresent,
          })
        }
      });
      requestData(that);
    }, getApp().globalData.time_span_call); 
  },


})