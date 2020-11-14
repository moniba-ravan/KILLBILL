module.exports = function doLogin(req,res, db,communicationsModel,nJwt,jalaali) {
  console.log('go home API');
  var counter =0;
  var decodedToken = nJwt.verify(req.header('token'),"secret", 'HS256');
  var id = decodedToken.body.id;
  var ObjectID = require('mongodb').ObjectID;
  var user;
  var flag = 0;
  var cursor = db.collection('users').find( ObjectID(id));
  cursor.forEach(function (User, err) {
    user = User;
    flag = flag + 5;
    }, function () {
      flag = flag +5;
      if(flag === 5){
      //user not found
        res.status(404).send({error : "user was not found"});
      }else {
        var communicationsModels = new communicationsModel();
        communicationsModels.internet = req.body.internet;
        communicationsModels.mobileBill = req.body.mobileBill;
        communicationsModels.phoneBill =req.body.phoneBill;
        communicationsModels.date = new Date();
        db.collection('users').update({"_id":ObjectID(id)},{$push :{communicationArray:communicationsModels}},function (err,updated) {
            res.json({success:true});
        });
        }
      });

}
