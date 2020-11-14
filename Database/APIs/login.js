module.exports = function doLogin(req, res,db, nJwt) {
  console.log('Login API');
  var flag = 0;
  var token;
  var cursor = db.collection('users').find({$and: [{ username: req.body.username}, {password: req.body.password}]});
    cursor.forEach(function (doc, err) {
      flag = flag + 5;
      var jwt = nJwt.create({"id":doc._id}, "secret", "HS256");
      token = jwt.compact();
      }, function () {
        flag = flag + 5;
        if(flag === 5){
            res.status(404).send({error:"user was not found"});
        }else {
          res.json({success: "true", "token": token});
        }
            });
}
