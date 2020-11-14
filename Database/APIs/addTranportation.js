module.exports = function doLogin(req,res, db,tranportationModels,nJwt,jalaali) {
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
        var tranportationModel = new tranportationModels();
        tranportationModel.general = req.body.general;
        tranportationModel.personal = req.body.personal;
        tranportationModel.date = new Date();

        db.collection('users').update({"_id":ObjectID(id)},{$push :{transportationArray:tranportationModel}},function (err,updated) {
            res.json({success:true});
        });
        }
      });

}
