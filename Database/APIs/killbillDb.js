module.exports= function(req,res,db,nJwt){
    console.log('PaymetrDB API');
    var resultArray = [];
    var cursor =db.collection('users').find();
    cursor.forEach(function(User,err){
      resultArray.push(User);
    },function(){
      res.json(resultArray);

    });
}
