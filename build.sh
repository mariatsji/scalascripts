#!/bin/sh
# todoscript
# edit this section
SCRIPTS_PATH=~/shellscripts
TODO_SCRIPT=todo
TODO_DB_FILENAME=todo.db

# builds the executable bash scala script
echo '#!/bin/sh' > $SCRIPTS_PATH/$TODO_SCRIPT
echo 'exec scala "$0" '$SCRIPTS_PATH/$TODO_DB_FILENAME'' >> $SCRIPTS_PATH/$TODO_SCRIPT
echo '!#' >> $SCRIPTS_PATH/$TODO_SCRIPT
cat src/main/scala/TodoScript.scala >> $SCRIPTS_PATH/$TODO_SCRIPT
chmod 755 $SCRIPTS_PATH/$TODO_SCRIPT

# filehandlerscript
# edit this section
FILE_SCRIPT=fileprocess

# builds the executable bash scala script
echo '#!/bin/sh' > $SCRIPTS_PATH/$FILE_SCRIPT
echo 'exec scala "$0" "$@"' >> $SCRIPTS_PATH/$FILE_SCRIPT
echo '!#' >> $SCRIPTS_PATH/$FILE_SCRIPT
cat src/main/scala/FileScript.scala >> $SCRIPTS_PATH/$FILE_SCRIPT
chmod 755 $SCRIPTS_PATH/$FILE_SCRIPT
