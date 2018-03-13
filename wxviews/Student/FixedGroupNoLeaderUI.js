// pages/grouplist/FixedGroupNoLeaderUI.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    seminarId:1,
  groupMethod:'固定分组',
  info:{
    "id": 28,
    "name": 28,
    "leader": {
    },
    "members": [
    ],
    "topics": [
      {
      }
    ],
    hasLeader:false,
    isLeader:false,
    isSelectedTopic:false,
    time:0
  }
  
  },
  beLeader: function () {
    var groupId = this.data.info.id;
    var IPPort = getApp().globalData.IPPort;
    var that = this;
    wx.request({
      url: IPPort + '/group/' + groupId + '/assign',
      header: {
        Authorization: 'Bearer ' + wx.getStorageSync('jwt')
      },
      method: 'PUT',
      success: function (data) {
        wx.request({
          url: IPPort + '/seminar/' + that.data.seminarId + '/group/my',
          method: 'GET',
          header: {
            Authorization: 'Bearer ' + wx.getStorageSync('jwt')
          },
          success: function (data) {
            that.setData({
              info: data.data,
              hasLeader: true,
              isLeader: true,
            })
        }
    })}
   // console.log(this.data.topic);
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      groupMethod: options.groupMethod,
      seminarId: options.seminarId,
      isSelectedTopic: false,
    });
    var seminarId = this.data.seminarId;
    //wx.setStorageSync("nexturlGroup" + seminarId, "./FixedGroupNoLeaderUI")
    var IPPort = getApp().globalData.IPPort;
    var that = this;
    wx.request({
      url: IPPort + '/seminar/' + seminarId + '/group/my',
      header: {
        Authorization: 'Bearer ' + wx.getStorageSync('jwt')
      },
      method: 'GET',
      success: function (data) {
        console.log("groop",data);
        that.setData({
          info: data.data,
          //["info.topics"]:""
        })
        if(that.data.info.leader!=null){
         
          that.setData({
            hasLeader: true,
          })
          if (that.data.info.leader.id==wx.getStorageSync("studentId"))
            that.setData({
              isLeader: true,
            })
        }
        console.log("to",that.data.info.topics)
        console.log("io", that.data.isSelectedTopic)
        if(that.data.info.topics.length != 0)
          that.setData({
            isSelectedTopic: true
          })
      }
    })
      
  },
  
  

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.setData({
      time: 0
    })
    this.requestData(this);
  },


  requestData: function (that) {
    if (that.data.time <= -1)
      return
    setTimeout(function () {
      that.setData({
        time: that.data.time + 1,
      })
      console.log(that.data.time);
      var seminarId = that.data.seminarId;
      //wx.setStorageSync("nexturlGroup" + seminarId, "./FixedGroupNoLeaderUI")
      var IPPort = getApp().globalData.IPPort;
      wx.request({
        url: IPPort + '/seminar/' + seminarId + '/group/my',
        header: {
          Authorization: 'Bearer ' + wx.getStorageSync('jwt')
        },
        method: 'GET',
        success: function (data) {
          that.setData({
            info: data.data,
            //["info.topics"]:""
          })
          //console.log('1111111111',that.data.info);
          if (that.data.info.leader != null) {
            that.setData({
              hasLeader: true,
            })
          if (that.data.info.leader.id == wx.getStorageSync("studentId")){
              that.setData({
                isLeader: true,
              })          
          }
          else {
            that.setData({
              isLeader: false,
            }) }
          }
          else{
            that.setData({
              hasLeader: false,
              isLeader: false,
            })
          }
          if (that.data.info.topics.length != 0)
            that.setData({
              isSelectedTopic: true
            })
        }
      })
      that.requestData(that);
    }, getApp().globalData.time_span_group);
  },




  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    //console.log('111111111111111111111')
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    //console.log('2222222222222222')
    this.setData({
      time: -2
    })
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  Leave: function () {
    var groupId = this.data.info.id;
    var IPPort = getApp().globalData.IPPort;
    var that = this;
    wx.request({
      url: IPPort + '/group/' + groupId + '/resign',
      header: {
        Authorization: 'Bearer ' + wx.getStorageSync('jwt')
      },
      method: 'PUT',
      success: function (data) {
        wx.request({
          url: IPPort + '/seminar/' + that.data.seminarId + '/group/my',
          header: {
            Authorization: 'Bearer ' + wx.getStorageSync('jwt')
          },
          method: 'GET',
          success: function (data) {
            console.log(that.data.isSelectedTopic);
            that.setData({
              info: data.data,
              hasLeader:false,
              isLeader:false,
            })
      }
        })
      }
    })
  },

  choose: function () {
    var groupId = this.data.info.id;
    var seminarId = this.data.seminarId;
    var groupMethod = this.data.groupMethod;
    wx.redirectTo({
      url: "./GroupChooseTopicUI?seminarId=" + seminarId + '&groupId=' + groupId + "&groupMethod=" + groupMethod,
      success: function (res) { },
      fail: function (res) { },
      complete: function (res) { },
    })
  },




})