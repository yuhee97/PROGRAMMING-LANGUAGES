#ifndef PARSER_H
#define PARSER_H

void expr();
void rexp();
void aexp();
void term();
void factor();

extern int nextToken;
#endif