"use strict";
var gulp = require('gulp');
var replace = require('gulp-replace');
var flatten = require('gulp-flatten');

gulp.task('default', ['copy']);

gulp.task('copy', function() {
  gulp.src("bower_components/vaadin-*/demo/**")
  .pipe(flatten({ includeParents: 1} ))
  .pipe(replace(/\.\.\/vaadin-(.*?)\.html/, '../bower_components/vaadin-$1/vaadin-$1.html'))
  .pipe(gulp.dest('.'));
});
