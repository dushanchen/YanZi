from django.shortcuts import render,HttpResponse

import json



def index(request):

    return render(request, 'index.html')
# Create your views here.
def yuanliti(request):

    dic = {
        #标题
        'dic1':{
            "block":2,
            "id": 1,
            "text": "",
            "type": 1002,
            "userId": 0,
         },
        #正文
        'dic2':{
            "id": 2,
            "text": "",
            "type": 1001,
            "userId": 0,
            "block":2,
            "index":1,
        },
        #正文加粗
        'dic3':{
            "id": 3,
            "text": "",
            "type": 1003,
            "userId": 0,
            "block":2,
            "index":2,
        },
        #正文图片1
        'dic4':{
            "id": 4,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":2,
            "index":3,
        },
        #正文图片2
        'dic5':{
            "id": 5,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":2,
            "index":4,
        },
        #正文图片3
        'dic6':{
            "id": 6,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":2,
            "index":5,
        },
        #正文图片4
        'dic7':{
            "id": 7,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":2,
            "index":6,
        },
        #正确答案
        'dic8':{
            "correctId": 13,
            "id": 8,
            "image": "",
            "text": "",
            "type": 3001,
            "userId": 0,
            "block":3,
            "index":1,
        },
        #错误答案
        'dic9':{
            "correctId": 0,
            "id": 9,
            "image": "",
            "text": "",
            "type": 3001,
            "userId": 0,
            "block":3,
            "index":2,
        },
        #过渡段/介绍段
        'dic10':{
            "id": 10,
            "text": "",
            "type": 6001,
            "index": 1,
            "userId": 0,
            "block":8,
        },
        #解释／知识锦囊
        'dic11':{
            "id": 11,
            "text": "",
            "type": 1111,
            "userId": 0,
            "block":4,
            "index":1,
        },
        #解释加粗
        'dic12':{
            "id": 12,
            "text": "",
            "type": 1001,
            "userId": 0,
            "block":4,
            "index":2,
        },

        'dic13':{
            "id": 13,
            "type": 5001,
            "userId": 0,
            "block":2,
            "index":7,
        },
        #解释图片1
        'dic14':{
            "id": 14,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":6,
            "index":1,
        },
        #解释图片2
        'dic15':{
            "id": 15,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":6,
            "index":2,
        },
        #解释图片3
        'dic16':{
            "id": 16,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":6,
            "index":3,
        },
        #解释图片4
        'dic17':{
            "id": 17,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":6,
            "index":4,
        },
        #问题图片1
        'dic18':{
            "id":18,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":1
        },
        #问题图片2
        'dic19':{
            "id":19,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":2
        },
        #问题图片3
        'dic20':{
            "id":20,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":3
        }


    }


    if request.method == "POST":

        action = request.POST.get('action', '')
        if action == 'tiankong':
            dic['dic1']['text'] = request.POST.get('title','')
            dic['dic2']['text'] = request.POST.get('content','')
            dic['dic3']['text'] = request.POST.get('bold1','')

            dic['dic4']['image'] = request.POST.get('image1','')
            dic['dic5']['image'] = request.POST.get('image2','')
            dic['dic6']['image'] = request.POST.get('image3','')
            dic['dic7']['image'] = request.POST.get('image4','')

            dic['dic8']['image'] = request.POST.get('rightimage','')
            dic['dic8']['text'] = request.POST.get('right','')

            dic['dic9']['image'] = request.POST.get('errorimage','')
            dic['dic9']['text'] = request.POST.get('error','')

            dic['dic10']['text'] = request.POST.get('intro','')
            dic['dic11']['text'] = request.POST.get('know','')
            dic['dic12']['text'] = request.POST.get('bold2','')

            dic['dic14']['image'] = request.POST.get('image5','')
            dic['dic15']['image'] = request.POST.get('image6','')
            dic['dic16']['image'] = request.POST.get('image7','')
            dic['dic17']['image'] = request.POST.get('image8','')

            dic['dic18']['image'] = request.POST.get('qimage1','')
            dic['dic19']['image'] = request.POST.get('qimage2','')
            dic['dic20']['image'] = request.POST.get('qimage3','')
            return HttpResponse(json.dumps(dic,ensure_ascii=False))

    return render(request, 'yuanliti.html')


def anliti(request):
    dic = {
        #标题
        'dic1':{
            "block":2,
            "id": 1,
            "text": "",
            "type": 1002,
            "userId": 0,
         },
         #正文
        'dic2':{
            "id": 2,
            "text": "",
            "type": 1001,
            "block":2,
            "index":1,
            "userId": 0,
        },
        #正文加粗
        'dic3':{
            "id": 3,
            "text": "",
            "block":2,
            "index":2,
            "type": 1002,
            "userId": 0,
        },
        #正文图片1
        'dic4':{
            "id": 4,
            "image": "",
            "type": 2001,
            "block":2,
            "index":3,
            "userId": 0,
        },
        #正文图片2
        'dic5':{
            "id": 5,
            "image": "",
            "block":2,
            "index":4,
            "type": 2001,
            "userId": 0,
        },
        #正文图片3
        'dic6':{
            "id": 6,
            "block":2,
            "index":5,
            "image": "",
            "type": 2001,
            "userId": 0,
        },
        #正文图片4
        'dic7':{
            "id": 7,
            "block":2,
            "index":6,
            "image": "",
            "type": 2001,
            "userId": 0,
        },
        #答案
        'dic8':{
            "correctId": 2,
            "id": 8,
            "image": "",
            "text": "",
            "block":3,
            "index":1,
            "type": 3002,
            "userId": 0,
        },
        #答案
        'dic9':{
            "correctId": 0,
            "id": 9,
            "block":3,
            "index":2,
            "image": "",
            "text": "",
            "type": 3002,
            "userId": 0,
        },
        #答案
        'dic10':{
            "correctId": 0,
            "id": 10,
            "block":3,
            "index":3,
            "image": "",
            "text": "",
            "type": 3002,
            "userId": 0,
        },
        #答案
        'dic11':{
            "correctId": 0,
            "id": 11,
            "block":3,
            "index":4,
            "image": "",
            "text": "",
            "type": 3002,
            "userId": 0,
        },
        #过渡段/介绍段
        'dic12':{
            "id": 12,
            "text": "",
            "block":"8",
            "type": 6001,
            "index": 1,
            "userId": 0,
        },
        #解释／知识锦囊
        'dic13':{
            "id": 13,
            "text": "",
            "type": 1111,
            "userId": 0,
            "block":4,
            "index":1,
        },
        #解释加粗
        'dic14':{
            "id": 14,
            "text": "",
            "type": 1001,
            "userId": 0,
            "block":4,
            "index":2,
        },
        #解释图片1
        'dic15':{
            "id": 15,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":6,
            "index":1,
        },
        #解释图片2
        'dic16':{
            "id": 16,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":6,
            "index":2,
        },
        #解释图片3
        'dic17':{
            "id": 17,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":6,
            "index":3,
        },
        #解释图片4
        'dic18':{
            "id": 18,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":6,
            "index":4,
        },
        #问题图片1
        'dic19':{
            "id":19,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":1,
        },
        #问题图片2
        'dic20':{
            "id":20,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":2,
        },
        #问题图片3
        'dic21':{
            "id":21,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":3,
        }

    }


    if request.method == "POST":

        action = request.POST.get('action', '')
        if action == 'anliti':

            dic['dic1']['text'] = request.POST.get('title','')
            dic['dic2']['text'] = request.POST.get('content','')
            dic['dic3']['text'] = request.POST.get('bold1','')

            dic['dic4']['image'] = request.POST.get('image1','')
            dic['dic5']['image'] = request.POST.get('image2','')
            dic['dic6']['image'] = request.POST.get('image3','')
            dic['dic7']['image'] = request.POST.get('image4','')

            dic['dic8']['image'] = request.POST.get('rightimage','')
            dic['dic8']['text'] = request.POST.get('right','')

            dic['dic9']['image'] = request.POST.get('error1image','')
            dic['dic9']['text'] = request.POST.get('error1','')

            dic['dic10']['image'] = request.POST.get('error2image','')
            dic['dic10']['text'] = request.POST.get('error2','')

            dic['dic11']['image'] = request.POST.get('error3image','')
            dic['dic11']['text'] = request.POST.get('error3','')

            dic['dic12']['text'] = request.POST.get('intro','')
            dic['dic13']['text'] = request.POST.get('know','')
            dic['dic14']['text'] = request.POST.get('bold2','')

            dic['dic15']['image'] = request.POST.get('image5','')
            dic['dic16']['image'] = request.POST.get('image6','')
            dic['dic17']['image'] = request.POST.get('image7','')
            dic['dic18']['image'] = request.POST.get('image8','')

            dic['dic19']['image'] = request.POST.get('qimage1','')
            dic['dic20']['image'] = request.POST.get('qimage2','')
            dic['dic21']['image'] = request.POST.get('qimage3','')

            return HttpResponse(json.dumps(dic,ensure_ascii=False))

    return render(request, 'anliti.html')


def dapeiti(request):
    dic = {
        #标题
        'dic1':{
            "block":2,
            "id": 1,
            "text": "",
            "type": 1002,
            "userId": 0,
         },
         #正文
        'dic2':{
            "id": 2,
            "text": "",
            "type": 1001,
            "userId": 0,
            "block":2,
            "index":1,
        },
        #正文加粗
        'dic3':{
            "id": 3,
            "text": "",
            "type": 1002,
            "userId": 0,
            "block":2,
            "index":2,
        },
        #正文图片1
        'dic4':{
            "id": 4,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":2,
            "index":3,
        },
        #正文图片2
        'dic5':{
            "id": 5,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":2,
            "index":4,
        },
        #正文图片3
        'dic6':{
            "id": 6,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":2,
            "index":5,
        },
        #正文图片4
        'dic7':{
            "id": 7,
            "image": "",
            "type": 2001,
            "userId": 0,
            "block":2,
            "index":6,
        },
        #题目一
        'dic8':{
            "id": 8,
            "image": "",
            "text": "",
            "type": 3003,
            "userId": 0,
            "block":3,
            "index":7,
        },
        #题目二
        'dic9':{
            "id": 9,
            "image": "",
            "text": "",
            "type": 3003,
            "userId": 0,
            "block":3,
            "index":8,
        },
        #题目三
        'dic10':{
            "id": 10,
            "image": "",
            "text": "",
            "type": 3003,
            "userId": 0,
            "block":3,
            "index":9,
        },
        #题目四
        'dic11':{
            "id": 11,
            "image": "",
            "text": "",
            "type": 3003,
            "userId": 0,
            "block":3,
            "index":10,
        },
        #题目五
        'dic12':{
            "id": 12,
            "image": "",
            "text": "",
            "type": 3003,
            "userId": 0,
            "block":3,
            "index":11,
        },
        #题目六
        'dic13':{
            "id": 13,
            "image": "",
            "text": "",
            "type": 3003,
            "userId": 0,
            "block":3,
            "index":12,
        },
        #题目一答案
        'dic14':{
            "correctId": 8,
            "id": 14,
            "text": "",
            "type": 3001,
            "userId": 0,
            "block":3,
            "index":13,
        },
        #题目二答案
        'dic15':{
            "correctId": 9,
            "id": 15,
            "text": "",
            "type": 3001,
            "userId": 0,
            "block":3,
            "index":18,
        },
        #题目三答案
        'dic16':{
            "correctId": 10,
            "id": 16,
            "text": "",
            "type": 3001,
            "userId": 0,
            "block":3,
            "index":16,
        },
        #题目四答案
        'dic17':{
            "correctId": 11,
            "id": 17,
            "text": "",
            "type": 3001,
            "userId": 0,
            "block":3,
            "index":15,
        },
        #题目五答案
        'dic18':{
            "correctId": 12,
            "id": 18,
            "text": "",
            "type": 3001,
            "userId": 0,
            "block":3,
            "index":14,
        },
        #题目六答案
        'dic19':{
            "correctId": 13,
            "id": 19,
            "text": "",
            "type": 3001,
            "userId": 0,
            "block":3,
            "index":17,
        },
        #过渡段/介绍段
        'dic20':{
            "id": 20,
            "text": "",
            "type": 6001,
            "index": 1,
            "userId": 0,
            "block":8,
        },
        #解释／知识锦囊
        'dic21':{
            "id": 21,
            "text": "",
            "type": 1111,
            "userId": 0,
            "block":1,
            "index":1,
        },
        #解释加粗
        'dic22':{
            "id": 22,
            "text": "",
            "block":1,
            "index":2,
            "type": 1001,
            "userId": 0,
        },
        #解释图片1
        'dic23':{
            "id": 23,
            "image": "",
            "block":1,
            "index":3,
            "type": 2001,
            "userId": 0,
        },
        #解释图片2
        'dic24':{
            "id": 24,
            "image": "",
            "block":1,
            "index":4,
            "type": 2001,
            "userId": 0,
        },
        #解释图片3
        'dic25':{
            "id": 25,
            "image": "",
            "block":1,
            "index":5,
            "type": 2001,
            "userId": 0,
        },
        #解释图片4
        'dic26':{
            "id": 26,
            "image": "",
            "block":1,
            "index":6,
            "type": 2001,
            "userId": 0,
        },
        #问题图片1
        'dic27':{
            "id":27,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":1,
        },
        #问题图片2
        'dic28':{
            "id":28,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":2,
        },
        #问题图片3
        'dic29':{
            "id":29,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":3,
        }

    }

    if request.method == "POST":

        action = request.POST.get('action', '')
        if action == 'dapeiti':

            dic['dic1']['text'] = request.POST.get('title','')
            dic['dic2']['text'] = request.POST.get('content','')
            dic['dic3']['text'] = request.POST.get('bold1','')

            dic['dic4']['image'] = request.POST.get('image1','')
            dic['dic5']['image'] = request.POST.get('image2','')
            dic['dic6']['image'] = request.POST.get('image3','')
            dic['dic7']['image'] = request.POST.get('image4','')

            dic['dic8']['image'] = request.POST.get('questionimage1','')
            dic['dic8']['text'] = request.POST.get('question1','')
            dic['dic9']['image'] = request.POST.get('questionimage2','')
            dic['dic9']['text'] = request.POST.get('question2','')
            dic['dic10']['image'] = request.POST.get('questionimage3','')
            dic['dic10']['text'] = request.POST.get('question3','')
            dic['dic11']['image'] = request.POST.get('questionimage4','')
            dic['dic11']['text'] = request.POST.get('question4','')
            dic['dic12']['image'] = request.POST.get('questionimage5','')
            dic['dic12']['text'] = request.POST.get('question5','')
            dic['dic13']['image'] = request.POST.get('questionimage6','')
            dic['dic13']['text'] = request.POST.get('question6','')

            dic['dic14']['text'] = request.POST.get('answer1','')
            dic['dic15']['text'] = request.POST.get('answer2','')
            dic['dic16']['text'] = request.POST.get('answer3','')
            dic['dic17']['text'] = request.POST.get('answer4','')
            dic['dic18']['text'] = request.POST.get('answer5','')
            dic['dic19']['text'] = request.POST.get('answer6','')

            dic['dic20']['text'] = request.POST.get('intro','')
            dic['dic21']['text'] = request.POST.get('know','')
            dic['dic22']['text'] = request.POST.get('bold2','')

            dic['dic23']['image'] = request.POST.get('image5','')
            dic['dic24']['image'] = request.POST.get('image6','')
            dic['dic25']['image'] = request.POST.get('image7','')
            dic['dic26']['image'] = request.POST.get('image8','')

            dic['dic27']['image'] = request.POST.get('qimage1','')
            dic['dic28']['image'] = request.POST.get('qimage2','')
            dic['dic29']['image'] = request.POST.get('qimage3','')

            return HttpResponse(json.dumps(dic,ensure_ascii=False))

    return render(request, 'dapeiti.html')

def shuomingti(request):
    dic = {
        #标题
        'dic1':{
            "block":2,
            "id": 1,
            "text": "",
            "type": 1002,
            "userId": 0,
         },
         #对话1
        'dic2':{
            "id": 2,
            "text": "",
            "type": 4001,
            "userId": 0,
            "block":7,
            "index":1,
        },
        #问题一
        'dic3':{
            "id": 3,
            "text": "",
            "type": 4001,
            "userId": 0,
            "block":7,
            "index":2,
        },
        #问题一正确回答
        'dic4':{
            "id": 4,
            "type": 3004,
            "text": "",
            "userId": 0,
            "parentId":3,
            "correctId":3,
            "block":7,
            "index":3,
        },
        #问题一回答二
        'dic5':{
            "id": 5,
            "type": 3004,
            "text": "",
            "userId": 0,
            "parentId":3,
            "correctId":0,
            "block":7,
            "index":4,
        },
        #问题一回答一的下一步说明
        'dic6':{
            "id": 6,
            "type": 4001,
            "text": "",
            "userId": 0,
            "parentId":4,
            "block":7,
            "index":5,
        },
        #问题一回答二的下一步说明
        'dic7':{
            "id": 7,
            "type": 4001,
            "text": "",
            "userId": 0,
            "parentId":5,
            "block":7,
            "index":6,
        },
        #第二个对话
        'dic8':{
            "id": 8,
            "text": "",
            "type": 4001,
            "userId": 0,
            "block":7,
            "index":7,
        },
        #问题二
        'dic9':{
            "id": 9,
            "text": "",
            "type": 4001,
            "userId": 0,
            "block":7,
            "index":8,
        },
        #问题二正确回答
        'dic10':{
            "id": 10,
            "text": "",
            "type": 3004,
            "userId": 0,
            "block":7,
            "index":9,
            "parentId":9,
            "correctId":9
        },
        #问题二错误回答
        'dic11':{
            "id": 11,
            "text": "",
            "type": 3004,
            "userId": 0,
            "block":7,
            "index":10,
            "parentId":9,
            "correctId":0
        },
        #问题二回答一的下一步说明
        'dic12':{
            "id": 12,
            "text": "",
            "type": 4001,
            "block":7,
            "index":11,
            "userId": 0,
            "parentId":10
        },
        #问题二回答二的下一步说明
        'dic13':{
            "id": 13,
            "text": "",
            "block":7,
            "index":12,
            "type": 4001,
            "userId": 0,
            "parentId":11
        },
        #第三个对话
        'dic14':{
            "id": 14,
            "text": "",
            "block":7,
            "index":13,
            "type": 4001,
            "userId": 0,
        },
        #第一个图片
        'dic15':{
            "id": 15,
            "image":"",
            "type": 2001,
            "userId": 0,
        },
        #第二个图片
        'dic16':{
            "id": 16,
            "image":"",
            "type": 2001,
            "userId": 0,
        },
        #第三个图片
        'dic17':{
            "id": 17,
            "image":"",
            "type": 2001,
            "userId": 0,
        },
        #第四个图片
        'dic18':{
            "id": 18,
            "image":"",
            "type": 2001,
            "userId": 0,
        },
        #问题图片1
        'dic19':{
            "id":19,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":1,
        },
        #问题图片2
        'dic20':{
            "id":20,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":2,
        },
        #问题图片3
        'dic21':{
            "id":21,
            "image":"",
            "type":2001,
            "userId":0,
            "block":1,
            "index":3,

        }

    }

    if request.method == "POST":

        action = request.POST.get('action', '')
        if action == 'shuomingti':

            dic['dic1']['text'] = request.POST.get('title','')


            texta1 = request.POST.get('duihua1','')
            texta1.replace('。','。\\r\\n')

            dic['dic2']['text'] = texta1
            dic['dic3']['text'] = request.POST.get('question1','')

            dic['dic4']['text'] = request.POST.get('right1','')
            dic['dic5']['text'] = request.POST.get('error1','')
            dic['dic6']['text'] = request.POST.get('rightshuoming1','')
            dic['dic7']['text'] = request.POST.get('errorshuoming1','')


            texta2 = request.POST.get('duihua2','')
            texta2.replace('。','。\\r\\n')

            dic['dic8']['text'] = texta2
            dic['dic9']['text'] = request.POST.get('question2','')

            dic['dic10']['text'] = request.POST.get('right2','')
            dic['dic11']['text'] = request.POST.get('error2','')

            dic['dic12']['text'] = request.POST.get('rightshuoming2','')
            dic['dic13']['text'] = request.POST.get('errorshuoming2','')


            texta3 = request.POST.get('duihua3','')
            texta3.replace('。','。\\r\\n')
            dic['dic14']['text'] = texta3


            dic['dic15']['image'] = request.POST.get('image1','')
            dic['dic16']['image'] = request.POST.get('image2','')
            dic['dic17']['image'] = request.POST.get('image2','')
            dic['dic18']['image'] = request.POST.get('image3','')

            dic['dic19']['image'] = request.POST.get('qimage1','')
            dic['dic20']['image'] = request.POST.get('qimage2','')
            dic['dic21']['image'] = request.POST.get('qimage3','')

            return HttpResponse(json.dumps(dic,ensure_ascii=False))

    return render(request, 'shuomingti.html')

def zongjie(request):
    dic = {
        #标题
        'dic1':{
            "title":"",
            "content": "",
            "index": 1,
         },

         'dic2':{
            "title":"",
            "content": "",
            "index": 2,
         },

         'dic3':{
            "title":"",
            "content": "",
            "index": 3,
         },

         'dic4':{
            "title":"",
            "content": "",
            "index": 4,
         },

         'dic5':{
            "title":"",
            "content": "",
            "index": 5,
         },

         'dic6':{
            "title":"",
            "content": "",
            "index": 6,
         },

         'dic7':{
            "title":"",
            "content": "",
            "index": 7,
         },

         'dic8':{
            "title":"",
            "content": "",
            "index": 8,
         },
    }
    if request.method == "POST":

        action = request.POST.get('action', '')
        if action == 'zongjie':
            dic['dic1']['title'] = request.POST.get('title1',''),
            dic['dic1']['content'] = request.POST.get('content1',''),

            dic['dic2']['title'] = request.POST.get('title2',''),
            dic['dic2']['content'] = request.POST.get('content2',''),

            dic['dic3']['title'] = request.POST.get('title3',''),
            dic['dic3']['content'] = request.POST.get('content3',''),

            dic['dic4']['title'] = request.POST.get('title4',''),
            dic['dic4']['content'] = request.POST.get('content4',''),

            dic['dic5']['title'] = request.POST.get('title5',''),
            dic['dic5']['content'] = request.POST.get('content5',''),

            dic['dic6']['title'] = request.POST.get('title6',''),
            dic['dic6']['content'] = request.POST.get('content6',''),

            dic['dic7']['title'] = request.POST.get('title7',''),
            dic['dic7']['content'] = request.POST.get('content7',''),

            dic['dic8']['title'] = request.POST.get('title8',''),
            dic['dic8']['content'] = request.POST.get('content8',''),

            return HttpResponse(json.dumps(dic,ensure_ascii=False))
    return render(request, 'zongjie.html')
