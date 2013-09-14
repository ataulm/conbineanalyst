## ConBiNe Analyst

Given a directory with a set of output files which have been produced by the [GOLEMlite][golemlite] application, **ConBiNe**, this tool parses the files to construct a large CSV table, and a series of charts.

Assumes:

1. directory content is static: the files aren't being modified while the processing is occurring, and files aren't being added (this is fine, but they'll be ignored) or removed (it will log an error, or in the worst case, crash).
2. \*.runhistory files are valid: files with the extension "runhistory" contain a series of `Events` in the prescribed textual format that the **ConBiNe** app outputs (as of Sept 14, 2013), with one `Event` per line, apart from the first line which are column headings.
3. File names follow the **ConBiNe** output convention: "X_Y.runhistory" where "X" is the current combination of variables, and "Y" indicates the run number (which repetition of the current combination this run represents).

[golemlite]: https://bitbucket.org/dice_rhul/golemlite