module.exports= function(req,res,db,nJwt){
    console.log('PaymetrDB API');
    var resultArray = [];
    var cursor =db.collection('users').find();
    cursor.forEach(function(User,err){
      db.collection('users').remove(User, function(err,us){
          if(err){
            console.log(err);
          }else{
              console.log('success : true');
          }

      });
    },function(){
      res.json({success : true});

    });
}
