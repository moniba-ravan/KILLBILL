module.exports = function doRegister(req, res, db,userModels,jalaali) {
  console.log('Register API');
  var flag =0;
     var cursor = db.collection('users').find({username:req.body.username});
     cursor.forEach(function(User,err){
       flag =flag +5;
     },function(){
       flag =flag +5;
       if (flag=== 5) {
         var userModel = new userModels();
         console.log(req.body.username);
         userModel.username=req.body.username;
         userModel.password = req.body.password;
         userModel.age=req.body.age;
         userModel.income=req.body.income;
         userModel.clothsArray =[];
         userModel.communicationArray =[];
         userModel.entertainmentArrary =[];
         userModel.furnitureArray =[];
         userModel.groceriesArray =[];
         userModel.stationeryArray =[];
         userModel.transportationArray =[];
         // var date = jalaali.toJalaali(new Date());
         // userModel.registerDate =date.jy +'/'+date.jm +'/'+ date.jd;
          userModel.registerDate =new Date();

         userModel.daysArray = [];
         db.collection('users').save(userModel,function(err){
             if(err){
               res.status(500).json({err:'error while saving data'});
             }else {
               res.json({success : true});
             }
           });

       }else {
         res.status(303).json({err :'this user exists'});
       }
     });
}
