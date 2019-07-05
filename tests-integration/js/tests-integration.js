if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'tests-integration'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'tests-integration'.");
}
this['tests-integration'] = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  function test1() {
    println(1);
  }
  function test3() {
    println(1);
  }
  _.test1 = test1;
  _.test3 = test3;
  Kotlin.defineModule('tests-integration', _);
  return _;
}(typeof this['tests-integration'] === 'undefined' ? {} : this['tests-integration'], kotlin);
