module.exports= function(req,res,db,nJwt){
    console.log('PaymetrDB API');
    var user;
    var flag =0;
    var cursor =db.collection('users').find({username : req.body.username});
    cursor.forEach(function(User,err){
      user = User;
      flag = flag +5;
    },function(){
      flag = flag +5;
      if (flag === 5) {
        // user not found
        res.status(404).json({err :" user is not found"});
      }else {
        // user found
        db.collection('users').remove(user, function(err,us){
            if(err){
                res.json("errr");
            }else{
                res.json({success : true});
            }

        });
      }
    });
}
